//
//  main.cpp
//  1061036S_insertion_sort
//
//  Created by Wayne Chen on 2017/9/18.
//  Copyright © 2017年 Wayne Chen. All rights reserved.
//

#include <iostream>
#include <fstream>
#include <vector>
#include <algorithm>
using namespace std;

class insertion_sort {
    public:
      int inputInit();
      int insertionSort();
    private:
      int readInput();
      int resultOut();
      int listSize;
      int index;
      vector<int> list;
};

int insertion_sort::inputInit() {
    char fileName[] = "input.txt";
    fstream fp;
    fp.open(fileName, ios::out);
    if(!fp) {
      cout << "Failed to open a file: " << fileName << endl;
      return EXIT_FAILURE;
    }
    cout << "File is opened successfully!" << endl;
    cout << "How many numbers do you want to generate for a list to be sorted? ";
    cin >> listSize;
    fp << listSize << endl;
    cout << "Which number do you want to know? Please enter the index of the number of interest.\n";
    cin >> index;
    fp << index << endl;
    for(int i=1; i<=listSize; i++) {
      fp << rand() % (listSize-1) +1 << " ";
    }
    fp << endl;
    fp.close();
    return 0;

}

int insertion_sort::readInput() {
    char fileName[] = "input.txt";
    int number;
    fstream fp;
    fp.open(fileName, ios::in);
    if(!fp) {
      cout << "Failed to open a file" << fileName << endl;
      return EXIT_FAILURE;
    }
    cout << "input.txt File is opened successfully!" << endl;
    fp >> number;
    listSize = number;
    cout << number << " numbers to sort." << endl;
    fp >> number;
    index = number;
    cout << "The index of the number to pick after sorting: " << number << endl;
    while(fp >> number) {
      list.push_back(number);
    }
    return 0;
}

int insertion_sort::insertionSort() {
    readInput();
    int j;
    for(int i=1; i<list.size(); i++) {
      j = i-1;
      if(list[i] < list[j]) {
        while(list[i] < list[j] && j>=0) {
          j--;
        }
        for(int k=i-1; k>j; k--) {
          iter_swap(list.begin()+k+1, list.begin()+k);
        }
      }
    }
  resultOut();
    return 0;
}

int insertion_sort::resultOut() {
    fstream fp;
    char fileName[] = "output.txt";
    fp.open(fileName, ios::out);
    if(! fp) {
      cout << "Failed to open a file: " << fileName << endl;
      return EXIT_FAILURE;
    }
    fp << list[index-1] << endl;
    cout << "File is opened successfully!" << endl;
    cout << "The " << index << "th number in the sorted list is fetched successfully!" << endl;
    fp.close();
    return 0;
}

int main() {
    insertion_sort sort;
    //sort.inputInit();
    sort.insertionSort();
    cout << "Completed insertion sort based on input.txt and output the result to output.txt" <<endl;
}

