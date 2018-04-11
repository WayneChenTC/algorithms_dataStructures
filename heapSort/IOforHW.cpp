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
  int readInput(int&, vector<int>&, int&);
  int resultOut(int&, int&);
};

int IOforHW::inputInit() {
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
		cout << "How many numbers do you want to generate for an array to be used?" << endl;
		cout << "REMINDER: The size of arrary should not exceed 2^20 or less than 2, otherwise prompt appears immediately." << endl;
		cin >> arraySize;
	} while(arraySize < 2 || arraySize > exp2(20));
	fp << arraySize << endl;
	for(long int i=1; i<=arraySize; i++) {
		fp << (rand() % arraySize+1) << " ";
	}
	fp << endl;
  do {
    cout << "Which number do you want to output?" << endl;
    cout << "REMINDER: The number of interest should be in the range of 1 to " << arraySize << endl;
    cin >> numIntrst;
  } while(numIntrst < 1 || numIntrst > arraySize);
  fp << numIntrst;
	fp.close();
	return 0;
}

int IOforHW::readInput(int& size, vector<int>& list, int& numIntrst) {
  char fileName[] = "input.txt";
	using DATA_TYPE = double;
	int num;
  DATA_TYPE number;
	fstream fp;
	fp.open(fileName, ios::in);
	if(!fp) {
		cout << "Failed to open a file" << fileName << endl;
		return EXIT_FAILURE;
	}
	cout << fileName << " is opened successfully!" << endl;
	fp >> num;
	size = num;
	//cout << "Begin to find the maximum subarray of the " << arraySize << "-element array." << endl;
	for(int i=1; i<= size; i++) {
    fp >> number;
		list.push_back(number);
	}
  fp >> numIntrst;
  fp.close();
	return 0;
}

int IOforHW::resultOut(int& numIntrst, int& num) {
  fstream fp;
	char fileName[] = "output.txt";
	fp.open(fileName, ios::out);
	if(! fp) {
		cout << "Failed to open a file: " << fileName << endl;
		return EXIT_FAILURE;
	}
	cout << "File is opened successfully!" << endl;
	cout << numIntrst << "th smallest number is " << num << endl;
	fp << num << endl;
	fp.close();
	return 0;
}
