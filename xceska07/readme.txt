# IJA - Project pacman 2022/2023
## Autoři
xceska07, xpejch08
##Překlad
Pro překlad a spuštění je využit nástroj maven.
jak postupovat:
1. v terminálu se přemístit do adresáře xceska07
2. v adresáři použít příkaz "mvn clean" pro vyčištění adresáře
3. v adresáři použít příkaz "mvn install" pro build pacmana a vygenerování dokumentace
4. v adresáři použít příkaz "mvn javafx:run" nebo "mvn javafx::run" pro spuštění hry
dokumentace se nachází v adresáři xceska07/target/apidocs a spouští se otevřením souboru index.html

##pacman hra
Uživatel má možnost spustit hru a vybrat si obtížnost, která určuje rychlost pacmana. Vybírání map přímo v
gui jsme jen připravili, ale nakonec nestihli implementovat. Po "zvolení" mapy a zvolení obtížnosti se spouští hra.
Uživatel se může pohybovat pomocí šipek. Při dotyku pacmana a ducha ztrácí pacman jeden život a teleportuje se
zpět na start. Interaktivní prvky jsou vidět na vrchu okna. Pokud pacman posbírá všechny klíče a přejde do cíle,
nebo mu dojdou životy, hra se ukončí s adekvátní zprávou pro uživatele.

##testování jiných map
Aplikace je připravena na načítání různě velkých map s různýmy objekty, v adresáři data jsou 2 mapy připraveny.
Jelikož jsme nestihli implementovat přepínání map uživatelem GUI, pokud chce uživatel otestovat/hrát jinou mapu,
musí změnit cestu k souboru mazeN.txt a to tak, že ve třídě Maze na řádku 325 změní název souboru maze1.txt na
název jiného textového dokumentu s bludištěm v daném adresáři data.