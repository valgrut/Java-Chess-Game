package Loader;


public interface IReader {
	String getNextLine();
	void printAll();
	void setInput(String inputFile);
	void openReader();
	void closeReader();
}
