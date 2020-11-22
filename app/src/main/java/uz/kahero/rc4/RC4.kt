package uz.kahero.rc4

class RC4 {
    private val s = arrayListOf<Int>()
    private val t = arrayListOf<Int>()
    private var keyArray = arrayListOf<Int>()
    private var text = arrayListOf<Int>()
    private val c = arrayListOf<Int>()

    fun generate(key: String, string: String): String {
        val keyLength = key.length
        s.clear()
        t.clear()
        c.clear()
        text.clear()
        keyArray.clear()
        keyArray = key.toCharArray().map { it.toInt() } as ArrayList<Int>
        text = string.toCharArray().map { it.toInt() } as ArrayList<Int>
        for (i in 0..255) {
            s.add(i)
            t.add(keyArray[i % keyLength])
        }
        var j = 0
        for (i in 0..255) {
            j = (j + s[i] + t[i]) % 256
            val temp = s[i]
            s[i] = s[j]
            s[j] = temp
        }
        var i = 0
        j = 0
        while (c.size != text.size) {
            i = (i + 1) % 256
            j = (j + s[i]) % 256
            val temp = s[i]
            s[i] = s[j]
            s[j] = temp
            val k = (s[i] + s[j]) % 256
            c.add(text[c.size] xor s[k])
        }
        var result = ""
        for (k in c) {
            result += k.toChar()
        }
        return result
    }
}