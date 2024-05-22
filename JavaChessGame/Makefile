javachess:
	mvn clean compile

.PHONY: test clean run package

test:
	mvn verify

clean:
	mvn clean

package: javachess
	mvn package

run: package
	java -jar target/chess-game-1.0-SNAPSHOT.jar
