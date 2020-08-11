package com.gomezrondon.tesseractocr

import net.sourceforge.tess4j.Tesseract
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.io.File

@SpringBootApplication
class Application:CommandLineRunner {
	override fun run(vararg args: String?) {
		val tesseract = Tesseract()
		tesseract.setDatapath("""tessdata""")

		val inputFile = File("image/"+args[0])
		val text = tesseract.doOCR(inputFile)

		if (!File("out-text").isDirectory) {
			File("out-text").mkdir()
		}
		val output ="out-text/"+inputFile.name.split(".")[0]+".txt"
		File(output).writeText(text)


	 	println(">>> output in folder: $output")
	}
}

fun main(args: Array<String>) {
	runApplication<Application>(*args)
}
