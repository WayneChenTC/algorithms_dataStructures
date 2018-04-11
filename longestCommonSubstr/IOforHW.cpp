#include <iostream>
#include <fstream>
#include <vector>
#include <algorithm>
#include <ctime>
#include <ctgmath>
using namespace std;

class IOforHW {
public:
  int inputInit();
  int readInput(int&, vector<int>&, vector<int>&);
  int resultOut(int&);
};

int IOforHW::inputInit() {
  srand(static_cast<int> (time(NULL)));    
  int arraySize;
  int numIntrst;
  srand(static_cast<int> (time(NULL)));
	char fileName[] = "input.txt";
	fstream fp;
	fp.open(fileName, ios::out);
	if(!fp) {
		cout << "Failed to open a file: " << fileName << endl;
		return EXIT_FAILURE;
	}
	cout << fileName << " is opened successfully!" << endl;
	do{
		cout << "How many numbers do you want to generate for the sequences to be used?" << endl;
		cout << "REMINDER: The size of the sequences should not exceed 20 or less than 2, otherwise prompts appear again." << endl;
		cin >> arraySize;
	} while(arraySize < 2 || arraySize > 20);
	fp << arraySize << endl;
	for(long int i=1; i<=arraySize; i++) {
		fp << (rand() % arraySize+1) << " ";
	}
	fp << endl;
    for(long int i=1; i<=arraySize; i++) {
		fp << (rand() % arraySize+1) << " ";
	}
	fp << endl;
  	fp.close();
	return 0;
}

int IOforHW::readInput(int& size, vector<int>& listA, vector<int>& listB) {
  char fileName[] = "input.txt";
  using DATA_TYPE = double;
  DATA_TYPE number;
	fstream fp;
	fp.open(fileName, ios::in);
	if(!fp) {
		cout << "Failed to open a file" << fileName << endl;
		return EXIT_FAILURE;
	}
	cout << fileName << " is opened successfully!" << endl;
	fp >> size;
	for(int i=1; i<= size; i++) {
    fp >> number;
		listA.push_back(number);
	}
    for(int i=1; i<= size; i++) {
    fp >> number;
		listB.push_back(number);
	}
    fp.close();
	return 0;
}

int IOforHW::resultOut(int& num) {
  fstream fp;
	char fileName[] = "output.txt";
	fp.open(fileName, ios::out);
	if(! fp) {
		cout << "Failed to open a file: " << fileName << endl;
		return EXIT_FAILURE;
	}
	cout << "File is opened successfully!" << endl;
	cout << "The length of longest common subseqence is " << num << endl;
    fp << num << endl;
	fp.close();
	return 0;
}
