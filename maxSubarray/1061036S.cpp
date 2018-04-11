//
//  main.cpp
//  1061036S_maxSubArray
//
//  Created by Wayne Chen on 2017/9/29.
//  Copyright © 2017年 Wayne Chen. All rights reserved.
//

#include <iostream>
#include <fstream>
#include <vector>
#include <algorithm>
#include <ctime>
#include <ctgmath>
using namespace std;

class max_subArray {
public:
	int inputInit();
	int readInput();
	void mainProcess();
	tuple<long, long, long> maxSubArray(vector<double>, long, long);
	tuple<long, long, long> maxCrossArray(vector<double>, long, long, long);
	int resultOut(double);
private:
    long arraySize = 0;
	vector<double> maxSub;
};

int max_subArray::inputInit() {
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
		cout << "REMINDER: The quantity of numbers should be between 2 and 1099511627776 inclusively." << endl;
		cin >> arraySize;
	} while(arraySize < 2 || arraySize > (long) exp2(40));
	fp << arraySize << endl;
	for(long int i=1; i<=arraySize; i++) {
		fp << (rand() % (long) (exp2(30)+exp2(30))) - (long) exp2(30)  << " ";
	}
	fp << endl;
	fp.close();
	return 0;
}

int max_subArray::readInput() {
	char fileName[] = "input.txt";
	using DATA_TYPE = double;
	long num;
    DATA_TYPE number;
	fstream fp;
	fp.open(fileName, ios::in);
	if(!fp) {
		cout << "Failed to open a file" << fileName << endl;
		return EXIT_FAILURE;
	}
	cout << fileName << " is opened successfully!" << endl;
	fp >> num;
	arraySize = num;
	cout << "Begin to find the maximum subarray of the " << arraySize << "-element array." << endl;
	while(fp >> number) {
		maxSub.push_back(number);
	}
	return 0;
}

void max_subArray::mainProcess() {
	int maxLow, maxHigh;
	double maxSum;
	readInput();
	tie(maxLow, maxHigh, maxSum) = maxSubArray(maxSub, 0, (long) maxSub.size()-1);
	cout << "The maximum subarray starts at " << maxLow << " to " << maxHigh <<" and it's sum is " << maxSum << "." << endl;
	resultOut(maxSum);
}

tuple<long, long, long> max_subArray::maxSubArray(vector<double> a, long low, long high) {
	using DATA_TYPE = double;
    long left_low, left_high, right_low, right_high, cross_low,
	cross_high;
	DATA_TYPE left_sum, right_sum, cross_sum;
    long mid;
	if(high==low) {
		return make_tuple(low, high, a[low]);
	} else {
		mid = (long) floor((low + high)/2);
		tie(left_low, left_high, left_sum) = maxSubArray(a, low, mid);
		tie(right_low, right_high, right_sum) = maxSubArray(a, mid+1, high);
		tie(cross_low, cross_high, cross_sum) = maxCrossArray(a,low, mid, high);
		if(left_sum >= right_sum && left_sum >= cross_sum) {
			return make_tuple(left_low, left_high, left_sum);
		} else if(right_sum >= left_sum && right_sum >= cross_sum) {
			return make_tuple(right_low, right_high, right_sum);
		} else {
			return make_tuple(cross_low, cross_high, cross_sum);
		}
	}
}

tuple<long, long, long> max_subArray::maxCrossArray(vector<double> a, long low, long mid, long high) {
	using DATA_TYPE = double;
	DATA_TYPE temp_rMax = INT_MIN, temp_lMax = INT_MIN;
	DATA_TYPE max_left, max_right;
	DATA_TYPE rSum = 0, lSum = 0;
	for(long i=mid; i>=low; i--) {  // don't use unsigned int as i's data type, this'll cause i-- returns the maximum value if i is already 0
		lSum += a[i];
		if(lSum > temp_lMax) {
			temp_lMax = lSum;
			max_left = i;
		}
	}
	for(long j=mid+1; j<= high; j++) {
		rSum += a[j];
		if(rSum > temp_rMax) {
			temp_rMax = rSum;
			max_right = j;
		}
	}
	return make_tuple(max_left, max_right, temp_rMax + temp_lMax);
}

int max_subArray::resultOut(double max_sum) {
	fstream fp;
	char fileName[] = "output.txt";
	fp.open(fileName, ios::out);
	if(! fp) {
		cout << "Failed to open a file: " << fileName << endl;
		return EXIT_FAILURE;
	}
	cout << "File is opened successfully!" << endl;
	cout << "The sum of the maximum subarray is " << max_sum << ", and it's recorded in output.txt!" << endl;
	fp << max_sum << endl;
	fp.close();
	return 0;
}

int main() {
	max_subArray max;
//	srand(static_cast<int> (time(NULL)));
//	int maxLow, maxHigh, maxSum;
//	vector<int> test;
//	for(int i=0; i<=10; i++) {
//		test.push_back((rand() % 19) - 9);
//	}
//	cout << "the array named test has the following members: " << endl;
//	for(int j: test) {
//		cout << j << " ";
//	}
//	cout << endl << "Start to find the maximum subarray of test." << endl;
//	tie(maxLow, maxHigh, maxSum) = max.maxSubArray(test, 0, test.size()-1);
//	cout << "The maximum subarray starts from the " << maxLow << "th element to the " << maxHigh <<"th element and it's sum is " << maxSum << "." << endl;
	//max.inputInit();
	max.mainProcess();
	return 0;
}
