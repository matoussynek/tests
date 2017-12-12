#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Cestmir Bernatsky

int velikost_pole(char *pole)
{
    int delka;
    for(delka=0; pole[delka] != '\0'; delka++);
    return delka;
}

int zacatek_string(char *text, char *subtext, int ukazatel)
{
    int i, j, vysledek;
    for(i=ukazatel; i<velikost_pole(text); i++)
    {
        if(text[i] == subtext[0])
        {
            vysledek = i;
            j = 1;
            i++;
            while(subtext[j] != '\0')
            {
                if(text[i] != subtext[j])
                {
                    break;
                }
                else
                {
                    i++;
                    j++;
                }
            }
            if(subtext[j] == '\0')
            {
                return vysledek;
            }
            else
            {
                i = vysledek;
            }
        }
    }
    return -1;
}


int d_elete(char* text, char* start, char* end)
{
//    int j = 0;
    int i = 0;

    int index_start = zacatek_string(text, start, 0);
    if(index_start<0)
    {
        printf("zacatek nenalezen");
        return -1;
    }
    int index_end = zacatek_string(text, end, index_start + velikost_pole(start));
    if(index_end<0)
    {
       printf("konec nenalezen");
       return -2;
    }

    printf("Puvodni text: %s\n%d\n%d\n", text, index_start, index_end);
    printf("Novy text: ");

    int k = 0;
    while(text[i] != '\0')
    {
        if(i<index_start || i>= index_end + velikost_pole(end))
        {
            text[k] = text[i];
            printf("%c",text[i]);
            k++;
        }
        i++;
    }
    text[k] = '\0';
    int smazane = index_end - index_start + velikost_pole(end);
    return smazane;


}


int main()
{
    int novy_text;
    char slovo[] = "int i;/* poznamka */int j;";
    novy_text = d_elete(slovo, "/*", "*/");
    printf("\nSmazanych znaku: %i\n Novy string: %s\n", novy_text, slovo);

    return 0;
}
