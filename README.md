# What it is
An example of Tesseract ocr with kotlin
this program reads an image and apply ocr then translate to spanish.

# Getting Started

* First you need to create an "image" folder at where the jar is
* put the image in the image folder
* execute:
with optional flags [ocr, translate]

Only Image to text
 ```
java -jar tesseract-ocr-0.0.1-SNAPSHOT.jar tss4j-test.png ocr
 ```

Only text translation
 ```
java -jar tesseract-ocr-0.0.1-SNAPSHOT.jar tss4j-test.txt translate
 ```
Both

 ```
java -jar tesseract-ocr-0.0.1-SNAPSHOT.jar tss4j-test.png ocr translate
 ```


