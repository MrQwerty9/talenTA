package com.sstudio.talenta

object Matrix {
    fun matrix1D(x: Int): Array<Double> {
        var arr = arrayOf<Double>()
        for (k in 0 until x) {
            arr += 0.0
        }
        return arr
    }

    fun matrix2D(x: Int, y: Int): Array<Array<Double>> {
        var arr2D = arrayOf<Array<Double>>()
        for (k in 0 until x) {
            var arr = arrayOf<Double>()
            for (k in 0 until y) {
                arr += 0.0
            }
            arr2D += arr
        }
        return arr2D
    }

    fun matrix3D(x: Int, y: Int, z: Int): Array<Array<Array<Double>>> {
        var arr3D = arrayOf<Array<Array<Double>>>()
        for (j in 0 until x) {
            var arr2D = arrayOf<Array<Double>>()
            for (k in 0 until y) {
                var arr = arrayOf<Double>()
                for (k in 0 until z) {
                    arr += 0.0
                }
                arr2D += arr
            }
            arr3D += arr2D
        }
        return arr3D
    }

    fun matrix4D(x: Int, y: Int, z: Int, w: Int): Array<Array<Array<Array<Double>>>> {
        var arr4D = arrayOf<Array<Array<Array<Double>>>>()
        for (i in 0 until x) {
            var arr3D = arrayOf<Array<Array<Double>>>()
            for (j in 0 until y) {
                var arr2D = arrayOf<Array<Double>>()
                for (k in 0 until z) {
                    var arr = arrayOf<Double>()
                    for (k in 0 until w) {
                        arr += 0.0
                    }
                    arr2D += arr
                }
                arr3D += arr2D
            }
            arr4D += arr3D
        }
        return arr4D
    }
}