# 알고리즘 면접 대비

### 퀵정렬/버블정렬 알고리즘을 설명할 수 있나요?
- 버블 정렬
    - 이중 반복문안에서 배열의 다음 인덱스 요소와 현재 인덱스 요소를 비교하고 요소를 swap하여 구현한다.
~~~
void BubbleSort(int DataSet[], int Length) {
	int temp;
	for (int i = 0; i < Length; i++)
		for (int j = 0; j < Length - (i+1); j++) {
			if (DataSet[j] > DataSet[j+1]) {
				temp = DataSet[j+1];
				DataSet[j+1] = DataSet[j];
				DataSet[j] = temp;
			}
		}
}
~~~

- 퀵 정렬
    - 1) 데이터 집합 내에서 임의의 기준 요소 선택
    - 2) 기준 요소보다 작은 요소들을 기준 요소 왼편, 큰 값은 오른편에 위치
    - 3) 기준 요소로 나뉘어 있는 집합에서 1,2번 반복
    - 4) 위 과정을 더 이상 데이터 집합을 나눌 수 없을 때 까지 반복
~~~
#include <stdio.h>

void Swap(int* A, int* B)
{
	int Temp = *A;
	*A = *B;
	*B = Temp;
}

int Partition(int DataSet[], int Left, int Right)
{
	int First = Left;
	//Pivot변수는 밑의 반복문에서 Left와 Right와 비교할 변수
	int Pivot = DataSet[First];
	
	//Pivot이 Left의 값이기 때문에 Pivot과 Left는 비교할 필요가 없으므로 Left++해 중복을 피한다
	++Left;
	
	//Left가 Right일때까지 반복한다
	while (Left <= Right)
	{
		//만약 배열의 요소가 99, 22, 31, 63이라고 쳤을 때
		//-> (오른쪽)방향으로 Right와 같을 때까지 DataSet[Left]가 Pivot보다 같거나 작을 때 반복
		while (DataSet[Left] <= Pivot && Left < Right)
			++Left;
		//<- (왼쪽)방향으로 Left와 같을 때까지 DataSet[Right]가 Pivot보다 같거나 클 때 반복
		while (DataSet[Right] >= Pivot && Left <= Right)
			--Right;

		//만약 Left가 Right보다 작을 경우 스왑해서 다시 반복
		if (Left < Right)
			Swap(&DataSet[Left], &DataSet[Right]);
		else
			break;
	}

	//
	Swap(&DataSet[First], &DataSet[Right]);
	 
	return Right;
}

void QuickSort(int DataSet[], int Left, int Right)
{
	//정렬할 요소가 2개 있을 경우
	if (Left < Right)
	{
		//Partition함수 : 
		//DataSet에 있는 특정 요소가 Left ~ Right범위안에서 검사되어 
		//해당 요소에 대한 고정 인덱스를 도출해준다
		int Index = Partition(DataSet, Left, Right);

		//고정 인덱스를 기준으로, 왼쪽범위에 대한 QuickSort함수, 오른쪽 범위에 대한 QuickSort함수를 호출한다
		QuickSort(DataSet, Left, Index - 1);
		QuickSort(DataSet, Index + 1, Right);
	}
}
~~~

- 버블 정렬은 요소를 하나씩 비교하기에 데이터가 많아질수록 느려지며 퀵 정렬은 임의 값을 기준으로 왼쪽, 오른쪽을 비교하며 정렬하기에 데이터가 많아질수록 최상위권으로 빨리 동작한다.

#### 출처
- https://studyfield.tistory.com/154