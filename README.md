# Corpus

Nlptoolkit’in birimlendirici/cümle bölücü bileşeni, bir serbest metnin birimlerini ve/veya cümlelerini saptamak için kullanılabilir. Bu bileşen, kural tabanlı bir bileşen olup girdiyi önceden belirlenmiş bir kural kümesini takip ederek cümlelere ve birimlerine ayırır. Bu kural kümesi, bir sonraki karakterin küçük/büyük harf olması gibi cümle düzeyinde kurallar içerdiği gibi, bir girdinin Türkçe’deki yaygın kısaltmalar arasında olup olmadığını kontrol etmek gibi dil düzeyinde kurallar da içerir. Özetle, birimlendirici/cümle bölücü bileşeni bir girdi olarak serbest metin alır ve çıktı olarak birimlerine ayrılmış bir cümle kümesi verir.

For Developers
============
You can also see [Python](https://github.com/olcaytaner/Corpus-Py), [C++](https://github.com/olcaytaner/Corpus-CPP), or [C#](https://github.com/olcaytaner/Corpus-CS) repository.

## Requirements

* [Java Development Kit 8 or higher](#java), Open JDK or Oracle JDK
* [Maven](#maven)
* [Git](#git)

### Java 

To check if you have a compatible version of Java installed, use the following command:

    java -version
    
If you don't have a compatible version, you can download either [Oracle JDK](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) or [OpenJDK](https://openjdk.java.net/install/)    

### Maven
To check if you have Maven installed, use the following command:

    mvn --version
    
To install Maven, you can follow the instructions [here](https://maven.apache.org/install.html).      

### Git

Install the [latest version of Git](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git).

## Download Code

In order to work on code, create a fork from GitHub page. 
Use Git for cloning the code to your local or below line for Ubuntu:

	git clone <your-fork-git-link>

A directory called Corpus will be created. Or you can use below link for exploring the code:

	git clone https://github.com/olcaytaner/Corpus.git

## Open project with IntelliJ IDEA

Steps for opening the cloned project:

* Start IDE
* Select **File | Open** from main menu
* Choose `Corpus/pom.xml` file
* Select open as project option
* Couple of seconds, dependencies with Maven will be downloaded. 


## Compile

**From IDE**

After being done with the downloading and Maven indexing, select **Build Project** option from **Build** menu. After compilation process, user can run Corpus.

**From Console**

Go to `Corpus` directory and compile with 

     mvn compile 

## Generating jar files

**From IDE**

Use `package` of 'Lifecycle' from maven window on the right and from `Corpus` root module.

**From Console**

Use below line to generate jar file:

     mvn install

## Maven Usage

        <dependency>
            <groupId>io.github.starlangsoftware</groupId>
            <artifactId>Corpus</artifactId>
            <version>1.0.1</version>
        </dependency>

------------------------------------------------

Detailed Description
============
+ [Corpus](#corpus)
+ [TurkishSplitter](#turkishsplitter)

## Corpus

Bir derlemi hafızaya atmak için

	a = Corpus("derlem.txt");

Bu derlem eğer noktalarla bölünmüş fakat cümlelere bölünmemiş ise

	Corpus(String fileName, SentenceSplitter sentenceSplitter)

Bu derlemin içinde Türkçe dışında cümleler de varsa, onları elimine etmek için

	Corpus(String fileName, LanguageChecker languageChecker)

Derlemdeki cümle sayısı

	int sentenceCount()

Derlemdeki i. cümle ise

	Sentence getSentence(int index)

## TurkishSplitter

Türkçe . kurallarına göre cümlelere ayırmak için TurkishSplitter sınıfı kullanılır.

	ArrayList<Sentence> split(String line);
