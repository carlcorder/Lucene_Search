![lucene](https://upload.wikimedia.org/wikipedia/commons/thumb/d/d1/Lucene_logo_green_300.png/220px-Lucene_logo_green_300.png)

How to build an index:

1. Create instance of Directory
2. Create instance of IndexWriterConfig
3. Instantiate IndexWriter using the directory and configuration
4. Create instances of Document for each document
5. Add documents to the IndexWriter
6. Commit changes to the IndexWriter
7. Close the IndexWriter

Once the [index](https://github.com/carlcorder/lucene-search/tree/master/src/main/java/com/vwr/luceneclass/vwr/index) has been created, you can browse it with [Luke](http://www.getopt.org/luke/)

* Luke 4.10 works with Lucene 4.10
* Run via the command line `java -XX:MaxPermSize=512m -jar luke-with-deps.jar`
