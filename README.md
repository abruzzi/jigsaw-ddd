### Jigsaw 

#### Setup

Prepare Database

```sh
mysql> create database jigsaw;
mysql> create database jigsaw_test;
```

#### Development 

To download all the dependencies and generate the Idea project:

```sh
gradle build idea
```

To run all the tests, you should firstly setup the database, and then

```
gradle test
```

