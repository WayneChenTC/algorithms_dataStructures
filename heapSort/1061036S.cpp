//
//  main.cpp
//  Heap Sort
//
//  Created by Wayne Chen on 2017/10/15.
//  Copyright © 2017年 Wayne Chen. All rights reserved.
//
#include <iostream>
#include <fstream>
#include <vector>
#include <algorithm>
#include <ctime>
#include <cmath>
#include "IOforHW.cpp"
using namespace std;

class heapSort {
public:
	void findNthNum(IOforHW &);
private:
	void buildHeap(vector<int>&);
	void removeMin(vector<int>&, vector<int>&);
	void bubbleDown(vector<int>&, int &, int &, int &);
};

void heapSort::buildHeap(vector<int>& list) {
	//bottom-up heapify an array in-place
	int height;
	int leafNum; //remember to convert leafNum to float, since int/int will be int instead of float. (no decimal fraction)
	int nonLeaf = 0;
	int listSize;
	int index;
	list.insert(list.begin(), '\0');
	list.push_back(INT_MAX); //back() returns element while end() returns iterator.
	listSize = (int) list.size() - 2;
	height = (int) floor(log2(listSize));
	for(int k=0; k<height; k++) {
		nonLeaf += (int) exp2(k);
	}
	leafNum = listSize - nonLeaf;
	for(int i=height-1; i>0; i--) {
		for(int j=0; j<exp2(i); j++) {
			index = exp2(i) + j;
			if(index*2 <= listSize &&
			   list[index] > min(list[index*2], list[index*2+1])){
				bubbleDown(list, index, height, listSize);
			} else {
				continue; //be careful on control flow
			}
		}
	}
	index = 1;
	if(index*2 <= listSize &&
	   list[1] > min(list[2], list[3])){
		bubbleDown(list, index, height, listSize);
	}
//	for(vector<int>::iterator it=list.begin()+1;it<list.end();it++) {
//		printf("%d\n", *it);
//	}
}

void heapSort::bubbleDown(vector<int>& list, int& index, int& height, int& size) {
	int tempMin;
	vector<int>::iterator temp;
	while((index*2) <= size &&
		  list[index] > min(list[index*2], list[index*2+1])) {
		tempMin = min(list[index*2], list[index*2+1]);
		temp = find(list.begin()+index*2, list.begin()+index*2+2, tempMin);
		iter_swap(list.begin()+index, temp);
		index = (int) (temp - list.begin());
	}
}

void heapSort::removeMin(vector<int>& result, vector<int>& list) {
	int height;
	int listSize;
	vector<int>::iterator temp;
	int index = 1;
	listSize = (int) list.size() - 2;
	height = (int) floor(log2(listSize));

	result.push_back(list[1]);
	iter_swap(list.begin()+1, list.end()-1);
	list.erase(list.end()-1);
	listSize -= 1;
	if((index*2) <= listSize &&
	   list[index] > min(list[index*2], list[index*2+1])) {
	    bubbleDown(list, index, height, listSize);
	}
}

void heapSort::findNthNum(IOforHW& io) {
	vector<int> list;
	vector<int> result;
	//time_t time;
	int numIntrst;
	int size;
	io.readInput(size, list, numIntrst);
	//time = clock();
	buildHeap(list);
	for(int i=0; i<size; i++) {
	    removeMin(result, list);
	}
	//time = clock() - time;
	//printf("Heap Sort spent %f seconds.\n", (float) time/CLOCKS_PER_SEC);
	io.resultOut(numIntrst, result[numIntrst-1]);
}

int main() {
	heapSort hs;
	IOforHW io;
	//io.inputInit();
	hs.findNthNum(io);
	return 0;
}
