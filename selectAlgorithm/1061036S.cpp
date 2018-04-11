//
//  main.cpp
//  select algorithm
//
//  Created by Wayne Chen on 2017/11/3.
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

class selectAlg {
public:
	int select(vector<int>&, int, int, int);
	int findPivot(vector<int>&, int, int);
	int partition(vector<int>&, int, int, int);
	void insertion_sort(vector<int>&, int, int);
};

int selectAlg::select(vector<int>& list, int r, int l, int k) {
	int pivot;
	int pivot_minus1;
	int size = 9;
	k = k+l-1;
	if(r-l+1 <= size) { //if size is too small, using insertion sort to find the NOI.
		insertion_sort(list, r, l);
		return k;
	}
	pivot = findPivot(list, r, l);
	pivot_minus1 = partition(list, r, l, pivot);
	if ((l+pivot_minus1)== k) {
		return l+pivot_minus1;
	} else {
		if(k < (l+pivot_minus1)) {
			return select(list, l+pivot_minus1-1, l, k);
		} else if (k > l+pivot_minus1-1) {
			return select(list, r, l+pivot_minus1+1, k-(l+pivot_minus1));
		}
	}
	return -1;
}

int selectAlg::findPivot(vector<int>& list, int r, int l) {
	int right=0;
	int size=9;
	int cnt = 0;
	int temp;
	int mid = ceil((float) size/2) - 1;
	vector<int> median;
	
	if(median.size() == 0) {
	    median.insert(median.begin(), '\0');
	}
	//insertion sorting every size-element groups
	for(int i=l; i<=r; i+=size) {
		if(i+(size-1) > r) {
			right = r;
		} else {
			right = i +(size-1);
		}
		insertion_sort(list,  right, i);
	}
	//collect medians in each group
	for(int i=l; i<=r; i+=size) {
		cnt++;
		if(i + mid > r) {
			temp = ceil((float) (r-i+1)/2);
			median.insert(median.begin()+cnt, list[i+temp-1]);
			break;
		}
		median.insert(median.begin()+cnt, list[i+mid]);
    }
	//find out the position of median of medians
	int pivotValue = select(median, cnt, 1, (int) ceil((float) cnt/2));
	for(int i=l; i<=r; i+=size) {
		if((i + mid > r) && (median[pivotValue] == list[i+ceil((float) (r-i+1)/2)-1])) {
			return i + ceil((float) (r-i+1)/2) - 1;
		}
		if(median[pivotValue] == list[i+mid]) {
			return i+mid;
		}
	}
	return -1;
}

int selectAlg::partition(vector<int> & list, int r, int l, int pivot) {
	int prefix = 0;
	iter_swap(list.begin()+pivot, list.begin()+r);
	for(int i=l; i<r; i++) {
		if(list[i] < list[r]) { //avoid using absolute position, apply relative position here
			iter_swap(list.begin()+prefix+1, list.begin()+i);
			prefix++;
		}
	}
	iter_swap(list.begin()+r, list.begin()+prefix+1);
	return prefix;
}

void selectAlg::insertion_sort(vector<int>& list, int r, int l) {
	int j;
	if(r != l) {
		for(int i=l+1; i<=r; i++) {
			j = i-1;
			if(list[i] < list[j]) {
				while(list[i] < list[j] && j>=l) {
					j--;
				}
				for(int k=i-1; k>j; k--) {
					iter_swap(list.begin()+k+1, list.begin()+k);
				}
			}
		}
	}
}

int main(int argc, const char * argv[]) {
	clock_t time;
	IOforHW io;
	selectAlg selectAlg;
	int size;
	int index;
	vector<int> list;
	int numInterest;
	//io.inputInit();
	io.readInput(size, list, numInterest);
	list.insert(list.begin(), '\0');
	time  = clock();
	index = selectAlg.select(list, size, 1, numInterest);
	time = clock() - time;
	printf("Spent %8.7f\n", ((float) time)/CLOCKS_PER_SEC);
	io.resultOut(numInterest, list[index]);
	return 0;
}
