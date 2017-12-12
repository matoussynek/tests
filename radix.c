#include <stdio.h>
#include <stdlib.h>

typedef struct
{
    int rok;
    int den;
    int mesic;
} datum;

typedef struct
{
    char* jmeno;
    datum date;
} osoba;


int k = 3000;

void insert_sort(osoba A[], int index, int size)
{
    int i = 1;
    while (i<size)
    {
        int j = i;
        switch(index)
        {
        case 0:
            {
                while (j>0 && (A[j-1].date.den > A[j].date.den))
                {
                    osoba temp = A[j];
                    A[j] = A[j-1];
                    A[j-1] = temp;
                    j--;
                }
                break;
            }
        case 1:
        {
            while (j>0 && (A[j-1].date.mesic > A[j].date.mesic))
            {
                    osoba temp = A[j];
                    A[j] = A[j-1];
                    A[j-1] = temp;
                    j--;
            }
            break;
        }
        case 2:
            {
                while (j>0 && (A[j-1].date.rok> A[j].date.rok))
                {
                    osoba temp = A[j];
                    A[j] = A[j-1];
                    A[j-1] = temp;
                    j--;
                }
                break;
            }
        }
        i++;
    }
}


void radix_sort(osoba A[], int d, int size)
{
    int i;
    for(i=0; i<d; i++)
    {
        insert_sort(A, i, size);
    }
}


int main()
{
    int i;

//    osoba pole[] = {osoba(Pavel, {1, 5, 1957}), osoba(Karel, {1, 9, 1955})};
    osoba pole[4];
    osoba osoba1, osoba2, osoba3, osoba4;

    osoba1.jmeno = "Pavel";
    osoba1.date.den = 11;
    osoba1.date.mesic = 5;
    osoba1.date.rok = 2000;

    osoba2.jmeno = "Karel";
    osoba2.date.den = 10;
    osoba2.date.mesic = 5;
    osoba2.date.rok = 2000;

    osoba3.jmeno = "Iveta";
    osoba3.date.den = 1;
    osoba3.date.mesic = 5;
    osoba3.date.rok = 1957;

    osoba4.jmeno = "Jana";
    osoba4.date.den = 29;
    osoba4.date.mesic = 11;
    osoba4.date.rok = 1879;

    pole[0] = osoba1;
    pole[1] = osoba2;
    pole[2] = osoba3;
    pole[3] = osoba4;
    int velikost = sizeof(pole)/sizeof(osoba);
    for(i=0; i<velikost; i++)
    {
        printf("%s\n", pole[i].jmeno);
    }

    radix_sort(pole, 3, velikost);
    printf("____________________________________________\n");
    for(i=0; i<velikost; i++)
    {
        printf("%s\n", pole[i].jmeno);
    }

    return 0;
}
