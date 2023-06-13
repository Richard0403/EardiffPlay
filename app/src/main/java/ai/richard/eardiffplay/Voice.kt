package ai.richard.eardiffplay

/**
 *
 * @ProjectName: EarDiffPlay
 * @Author: wuzhiguo@aiforward.com
 * @CreateDate: 2022/3/12 10:41 下午
 * @Description:
 */
object Voice {
    val wordArray = mutableListOf<String>("八[ba]", "趴[pa]", "搭[da]", "它[ta]", "伽[ga]", "喀[ka]")
    //八[ba] 趴[pa] 搭[da] 它[ta] 伽[ga] 喀[ka]
    val guanArray = mutableListOf(R.raw.guan_ba, R.raw.guan_pa, R.raw.guan_da, R.raw.guan_ta, R.raw.guan_ga, R.raw.guan_ka)
    val wuArray = mutableListOf(R.raw.wu_ba, R.raw.wu_pa, R.raw.wu_da, R.raw.wu_ta, R.raw.wu_ga, R.raw.wu_ka)
    val xiangArray = mutableListOf(R.raw.xiang_ba, R.raw.xiang_pa, R.raw.xiang_da, R.raw.xiang_ta, R.raw.xiang_ga, R.raw.xiang_ka)
    val ganArray = mutableListOf(R.raw.gan_ba, R.raw.gan_pa, R.raw.gan_da, R.raw.gan_ta, R.raw.gan_ga, R.raw.gan_ka)
    val yueArray = mutableListOf(R.raw.yue_ba, R.raw.yue_pa, R.raw.yue_da, R.raw.yue_ta, R.raw.yue_ga, R.raw.yue_ka)
    val minArray = mutableListOf(R.raw.min_ba, R.raw.min_pa, R.raw.min_da, R.raw.min_ta, R.raw.min_ga, R.raw.min_ka)
    val puArray = mutableListOf(R.raw.pu_ba, R.raw.pu_pa, R.raw.pu_da, R.raw.pu_ta, R.raw.pu_ga, R.raw.pu_ka)


    fun getWordsByTypeAndRawId(type: Int, id: Int): String {
        val index: Int = when (type){
            GUAN-> guanArray.indexOf(id)
            WU-> wuArray.indexOf(id)
            XIANG-> xiangArray.indexOf(id)
            GAN-> ganArray.indexOf(id)
            YUE-> yueArray.indexOf(id)
            MIN-> minArray.indexOf(id)
            PU-> puArray.indexOf(id)
            else -> puArray.indexOf(id)
        }
        return wordArray[index]
    }

    fun getVoiceResourceR(type: Int): Int {
        when (type){
            GUAN-> return guanArray[(0..5).random()]
            WU-> return wuArray[(0..5).random()]
            XIANG-> return xiangArray[(0..5).random()]
            GAN-> return ganArray[(0..5).random()]
            YUE-> return yueArray[(0..5).random()]
            MIN-> return minArray[(0..5).random()]
            PU-> return puArray[(0..5).random()]
        }
        return return puArray[(0..5).random()]
    }

    fun getVoiceResourceL(type: Int): Int {
        when (type){
            GUAN-> return guanArray[(0..5).random()]
            WU-> return wuArray[(0..5).random()]
            XIANG-> return xiangArray[(0..5).random()]
            GAN-> return ganArray[(0..5).random()]
            YUE-> return yueArray[(0..5).random()]
            MIN-> return minArray[(0..5).random()]
            PU-> return puArray[(0..5).random()]
        }
        return return puArray[(0..5).random()]
    }

    const val GUAN: Int = 0x01
    const val WU: Int = 0x02
    const val XIANG: Int = 0x03
    const val GAN: Int = 0x04
    const val YUE: Int = 0x05
    const val MIN: Int = 0x06
    const val PU: Int = 0x07
}