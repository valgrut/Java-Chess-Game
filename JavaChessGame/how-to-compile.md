# How to compile project

## Resources

- https://mvnrepository.com/search?q=javafx-app


## Tested versions

- openjdk 16
- Apache Maven 3.9.1

## How to run the app

- 1. Install dependencies

    ```
    sudo dnf install maven
    sudo dnf install java-latest-openjdk
    # sudo dnf install java-17-openjdk-devel
    ```
    
- 2. Make sure JAVA_HOME is set
    
    - Insert this into .bashrc
    
    ```
    export JAVA_HOME=$(readlink -f /usr/bin/javac | sed "s:/bin/javac::")
    ```
    
    - Apply changes:
    
    ```
    source ~/.bashrc
    ```

### How to compile and run app - Simple

!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
- 2. Compile and run

    ```
    mvn clean javafx:run
    ```
!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

### How to compile and run project - longer

- 2. Compile project (Create "binaries")

    Takes pom.xml file containing build instructions and generates class files.

    They contain bytecode instructions that the Java Virtual Machine (JVM) interprets and executes.

    ```
    mvn compile 
    ```

- 3. Create executable

    ```
    mvn package
    ```

- 4. Run the app

    ```
    java -jar target/chess-game.jar
    ```

- 5. Verify the package content

    ```
    # List files
    jar tvf your_jar_file.jar
    
    # Extract file
    jar xf target/chess-game-1.0-SNAPSHOT.jar path/in/archive/MANIFEST.MF
    ```

- 6. OR just

    ```
    mvn install
    ```

