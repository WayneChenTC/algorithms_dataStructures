#include <iostream>       // std::cout
#include <queue>          // std::priority_queue
#include <vector>         // std::vector
#include <functional>     // std::greater
#include <fstream>
#include <algorithm>
#include <cmath>
#include <ctgmath>
#include <string>
#include <assert.h>
using namespace std;

typedef struct treeNode node;
vector<int> freq; //priority queue
string str;	  //string to be encoded
int size;

struct treeNode {
	int freq;
	char value;
	shared_ptr<node> lChild;
	shared_ptr<node> rChild;
	shared_ptr<node> parent;
	string code;
	bool leaf;
	bool left;
};

int getInput() {
	char fileName[] = "input.txt";
	//using DATA_TYPE = double;
	int number;
	fstream fp;
	fp.open(fileName, ios::in);
	if(!fp) {
		cout << "Failed to open a file" << fileName << endl;
		return EXIT_FAILURE;
	}
	//cout << fileName << " is opened successfully!" << endl;
	fp >> size;
	for(int i=1; i<= size; i++) {
		fp >> number;
		freq.push_back(number);
	}
	fp >> str;
	fp.close();
	return 0;
}

class myComparison
{
public:
	bool operator() (const node& lhs, const node& rhs) const
	{
		return lhs.freq > rhs.freq; //used to implement a min priority queue
	}
};

void dfsEncode(node& curNode, string code) {
	curNode.code = code + curNode.code;
	if(curNode.leaf != true) {
		if(curNode.lChild != nullptr) {
			dfsEncode(*(curNode.lChild), curNode.code);
		}
		if(curNode.rChild != nullptr) {
			dfsEncode(*(curNode.rChild), curNode.code);
		}
	}
}

int resultOut(string code) {
	fstream fp;
	char fileName[] = "output.txt";
	fp.open(fileName, ios::out);
	if(! fp) {
		cout << "Failed to open a file: " << fileName << endl;
		return EXIT_FAILURE;
	}
	//cout << "File is opened successfully!" << endl;
	fp << code << endl;
	fp.close();
	return 0;
}

void huffmanCode() {
	char alpha[10] = {'a','b','c','d','e','f','g','h','i','j'};
	node charNode[size];
	vector<shared_ptr<node> > leaves;
	shared_ptr<node> newLeft;
	shared_ptr<node> newRight;
	shared_ptr<node> newNode;
	for(int i=0; i<size; i++) {
		node temp;
		temp.value = alpha[i];
		temp.freq = freq[i];
		temp.leaf = true;
		temp.lChild = nullptr;
		temp.rChild = nullptr;
		temp.parent = nullptr;
		charNode[i] = temp;
	}
	/*for(int i=0; i<size; i++) {
	 printf("%c with value %d\n",charNode[i].value, charNode[i].freq);
	 }*/
	priority_queue<node, vector<node>, myComparison > codeTree(charNode, charNode+size);
	/*for(int i=0; i<size; i++) {
	 node t;
	 t= codeTree.top();
	 codeTree.pop();
	 printf("%c with %d\n", t.value, t.freq);
	 }*/
	for(int i=1; i<size; i++) {
		shared_ptr<node> newLeft(new node);
		shared_ptr<node> newRight(new node);
		shared_ptr<node> newNode(new node);
		newNode->leaf = false;
		newNode->parent= nullptr;
		*newLeft = codeTree.top();
		newNode->lChild = newLeft;
		if(newNode->lChild->leaf == true) {
			leaves.push_back(newLeft);
		}
		newNode->lChild->parent = newNode;
		newNode->lChild->code = '0';
		newNode->lChild->left = true;
		codeTree.pop();
		*newRight = codeTree.top();
		newNode->rChild = newRight;
		if(newNode->rChild->leaf == true) {
			leaves.push_back(newRight);
		}
		newNode->rChild->parent = newNode;
		newNode->rChild->code = '1';
		newNode->rChild->left = false;
		codeTree.pop();
		newNode->freq = newNode->lChild->freq + newNode->rChild->freq;
		newNode->code = "";
		codeTree.push(*newNode);
	}
	shared_ptr<node> startNode(new node);
	*startNode = codeTree.top();
	dfsEncode(*startNode, "");
	string result = "";
	shared_ptr<node> itr;
	for(int i=0; i<str.size(); i++) {
		for(int j=0; j<size; j++) {
			itr = leaves[j];
			if(str[i] == itr->value) {
				result += itr->code;
				break;
			}
		}
	}
	resultOut(result);
	/*
	for(int i=0; i<size; i++) {
		shared_ptr<node> temp;
		temp = leaves[i];
		printf("char %c with frequency %d is encoded as %s\n", temp->value, temp->freq, temp->code.c_str());
	}
	*/
}

int main() {
	getInput();
	huffmanCode();
}



