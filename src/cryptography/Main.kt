package cryptography

import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import java.io.IOException
import javax.imageio.ImageIO

fun main() {
    var isActive=true
    while (isActive){
        println("Task (hide, show, exit):")
        when(val input= readln()){
            "exit" -> isActive=false
            "hide" -> hide()
            "show" -> println("Obtaining message from image.")
            else -> println("Wrong task: $input")
        }
    }
    println("Bye!")

}

fun hide(){
    val (inputImageName, outputImageName) = readInputs()
    val inputImageFile=File(inputImageName)
    try {
        val inputImage = ImageIO.read(inputImageFile)
        val outputImage = BufferedImage(inputImage.width, inputImage.height, BufferedImage.TYPE_INT_RGB)
        println("Input image: $inputImageName")
        println("Output image: $outputImageName")
        val height = inputImage.height
        val width = inputImage.width
        for (i in 0 until height) {
            for (j in 0 until width) {
                val rgb = inputImage.getRGB(j, i)
                val newColor = invertBitInColor(rgb)
                outputImage.setRGB(j, i, newColor.rgb)
            }
        }
        ImageIO.write(outputImage, "png", File(outputImageName))
        println("Image $outputImageName is saved.")
    } catch(e:IOException){
        println("Can't read input file!")
    }
}

private fun readInputs(): Pair<String, String> {
    println("Input image file:")
    val inputImageName = readln()
    println("Output image file:")
    val outputImageName = readln()
    return Pair(inputImageName, outputImageName)
}

private fun invertBitInColor(rgb: Int): Color {
    val color = Color(rgb)
    return Color(
        setLeastSignificantBitToOne(color.red),
        setLeastSignificantBitToOne(color.green),
        setLeastSignificantBitToOne(color.blue)
    )
}

fun setLeastSignificantBitToOne(value:Int):Int{
    return value or 1
}



