# Утилита фильтрации содержимого файлов "FileFilter"
## Параметры сборки и используемые зависимости:
- Java 21;
- Cистема сборки проекта - Maven 3 (Version 3.9.8);
- Библиотека парсинга опций и аргументов командной строки - args4j версии 2.37.
```xml
<dependency>
   <groupId>args4j</groupId>
   <artifactId>args4j</artifactId>
   <version>2.37</version>            
</dependency>
```
## Особенности реализации:
- Целые числа без ограничений по размеру;
- Вещественные числа ограничены диапазоном Double. Разделителем является точка. 
При использовании разделителем запятой число будет считаться строкой;
- Параметры командной строки:
```
 -a (--append)                : Дополнять существующие файлы с результатами
                                (default: false)
 -f (--fullStat)              : Показать полную статистику (default: false)
 -o (--outputPath) OUTPUT_DIR : Путь для результатов (default: .)
 -p (--prefix) PREFIX         : Префикс имен выходных файлов (default: )
 -s (--shortStat)             : Показать краткую статистику (default: false)
```
## Инструкция по запуску
- клонировать проект на АРМ с требуемой JDK и Maven;
- c использованием Maven загрузить зависимость;
- Выполнить команду Maven "package" и запустить или через IDE с использованием 
требуемых параметров командной строки или исполняемый файл "jar-with-dependencies" из каталога "target". 

```
Примеры команд запуска jar:

 java -jar FileFilter-1.0-SNAPSHOT-jar-with-dependencies.jar in1.txt
 java -jar FileFilter-1.0-SNAPSHOT-jar-with-dependencies.jar in1.txt in2.txt -a -f
```



