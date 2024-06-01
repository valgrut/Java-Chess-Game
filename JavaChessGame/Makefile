javachess:
	mvn clean compile

.PHONY: test clean run package

test:
	mvn verify

clean:
	mvn clean

package: javachess
	mvn package

run:
	mvn clean javafx:run
	# java -jar target/chess-game-1.0-SNAPSHOT.jar
