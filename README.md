# HSR Equipment Calculator

## Overview

This equipment calculator uses the Tesseract OCR software to parse an image for data, and applies weighted values to the stats to calculate a min-max rating for the equipment. The program may be upgraded from a CLI to an actual application in the future, TBD.

## Usage

After you finish rolling your equipment, take a screenshot containing only the equipment level and stats.

![Screenshot](https://github.com/namskram/equipment-calculator/blob/main/equipment-calculator/src/main/resources/test-image.png)

After running the application, it will prompt for the name of the character using the equipment, followed by the name of the image file. 

```
Enter character name: 
Enter filename:
```

After inputting the image, the equipment calculator will provide detailed information about the equipment, followed by an overall score. The calculator will assume that the BIS main stat and relic are used.

![Output](https://github.com/namskram/equipment-calculator/blob/main/equipment-calculator/src/main/resources/output1.png)

Relic scores are based off of the MobileMeta Relic Score tool, and a score of about 15 is ideal for any given character.

![Output 2](https://github.com/namskram/equipment-calculator/blob/main/equipment-calculator/src/main/resources/output2.png)

You can then change characters, or input a new image file to analyze more equipment. At any time, you can simply enter "b" to go back, or "q" to quit.

![Output 3](https://github.com/namskram/equipment-calculator/blob/main/equipment-calculator/src/main/resources/output3.png)

---

Last update: August 19, 2024 *(No M7 hunt)*
