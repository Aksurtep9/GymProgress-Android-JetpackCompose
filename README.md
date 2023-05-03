# Házi feladat specifikáció

Információk [itt](https://viauav21.github.io/laborok/hf)

## Androidalapú szoftverfejlesztés
### [2023. 04. 25. - 2023 1. félév]
### [Petruska Bence] - ([JP5JDU])
### [bence.petruska@gmail.com] 
### Laborvezető: [Pomázi Krisztián]

## Bemutatás

Az alkalmazás neve Progress Tracker, ami az edzéseink során jön jól. Amint elkezd az ember konditerembe járni, rájön, hogy egyre nehezebb a teljesítményünket követni, ebben segít az alkalmazás
Miből? Mennyit? Hányszor? Mikor? Ezeket a kérdéseket edzés közben pihenések között megválaszoljuk és az alkalmazás elmenti nekünk. 

## Főbb funkciók

Az alkalmazás körülbelül 3-4 főnézettel és pár alnézettel fog rendelkezni.
Az alkalmazást elindítva a **History nézet** a folyamatban lévő / befejezett edzések listája jelenik meg. Ebben a nézetben indíthatunk el új edzés "session"-t is. 
A **History nézetben** lehetőségünk van egy térkép megnyitására is, amiben látható, hogy hol edzettünk.
A listában egy edzésre rákattintva megjelenik az **Edzés nézet**, az adott edzéshez tartozó elvégzett gyakorlatok listája. Hozzáadhatunk illetve törölhetünk gyakorlatokat a nézetben, illetve szintén rákattinthatunk listaelemekre, ahol módosíthatjuk a súlyokat és ismétlést is.

A **History nézet** mellett található egy **Progress nézet**, amiben egy jetpack-compose chart library használatával kirajzolódik egy csinos kis grafikon az előrehaladásunkról.
Ebben a nézetben adhatjuk meg a súlyunkat is, amit szintén nyomon fog tudni követni így az applikáció.

Az alkalmazás perzisztensen fogja tárolni az adatokat **Room**-al.
A chart library valószínűleg ez lesz: [https://github.com/hi-manshu/Charty](https://github.com/hi-manshu/Charty) (de ez még változhat)
Az edzéshez elmentődik az edzés lokációja is és a dátum.
## Választott technológiák:

Az alkalmazás fejlesztése során használt technológiák tételes felsorolása. Az, hogy mi számít technológiának a laborokon ismertetésre kerül, a laborvezetőkkel tovább pontosítható. 
5 technológia használata javasolt. Például:

- UI: A felhasználói felület Jetpack Compose-ban és MVVM architektúrával.
- lista: Több listanézet: edzések, gyakorlatok, ismétlések... 
- Téma: A chart-hoz: [https://github.com/hi-manshu/Charty](https://github.com/hi-manshu/Charty) 
- Adatbáziskezelés: A felhasználó adatai elmentődnek.
- Pozíciómeghatározás: Az alkalmazás követi a pozíciónkat

___

# Házi feladat dokumentáció

### [Alkalmazás neve]

<img src="./assets/icon.png" width="160">

**Legkésőbb a dokumentáció fázisban lecserélendő a saját ikonnal!**

## Bemutatás

Az alkalmazás rövid, 2-3 mondatos bemutatása. Honnan az ötlet, mi szülte az igényt, ki lehetne a célközönség.
A laboron és előadáson bemutatott alkalmazásokat nem lehet házi feladatnak választani.

## Főbb funkciók

Az alkalmazás minden funkciójára kiterjedő leírás. Legyen egyértelműen eldönthető, hogy az adott funkció implementálva van-e!
P.l.: Az alkalmazással lehetőség van térképen megjeleníteni az állomáspontokat és azok A,B,C,D tulajdonságai meg is jelennek (ha elérhetőek).


## Felhasználói kézikönyv

Az alkalmazás minden funkciójára kiterjedő, teljes körű felhasználói leírás. Az összes releváns képernyőhöz/funkcióhoz tartalmaznia kell képernyőképet!

A képernyőképekkel kapcsolatos követelmények:

- Android Device Art Generator-ral telefont/tabletet kell rajzolni a képernyő köré!
	- Mindegy, hogy melyik készüléket választod, de legyen egységes az egész dokumentumban!
	- Telefonra tervezett képernyőket valamelyik telefon skin-nel, tablet képernyőt (amennyiben készítettél külön) tablet skin-nel készítsünk!
- Álló képernyőket álló módban, fekvőket fekvő módban rakjuk be! (Értelemszerűen. Ha fekvő képernyőképet húzol be a generator-ba, akkor fekvő módban rajzolja köré az eszközt)
-	Minden képhez legyen képaláírás, ami leírja hogy mit kell nézni a képen!
-	A képeket úgy méretezzük, hogy álló telefon méretből kettő elférjen egymás mellett egy sorban (fekvő illetve tablet képeket ehhez viszonyítva nagyítsuk)!
-	Amennyiben gesztúra vezérlést akarunk bemutatni a képernyőn, jelezzük a gesztúrát is! (ld példa kép)
-	A képeket és a képaláírásokat középre igazítsuk!

<p align="center">
<img src="./assets/image1.png" width="320">
<img src="./assets/image2.png" width="320">

1. ábra: Gesztúrával és gombbal is navigálható képernyők, hasznos kényelmi funkció a felhasználónak ha több lehetőséget is biztosítunk a navigációra
</p>

## Felhasznált technológiák:

Itt kell felsorolni minden technológiát, technikát, külső könyvtárat, komplexebb algoritmust, ami növeli az alkalmazás értékét. Osztályzáskor ezt a fejezetet nézzük meg először.

Külső osztálykönyvtár használata esetén a könyvtár neve legyen link, ami annak elérhetőségére mutat.

A kulcsszavak legyenek **félkövér** betűtípussal szedve.
Például:

- •	Az X és Y képernyők optimalizáltak **álló és fekvő nézetre** is
- [YCharts](https://github.com/yml-org/YCharts) osztálykönyvtár használata a grafikonok rajzolására
- **Fused Location API** használata helymeghatározásra
- **SQLite** alapú adattárolás
- Implicit intent használata **QR kód beolvasáshoz** (telepített Barcode Scanner alkalmazás szükséges a futtatásához)
- A játék fizikáját a [Box2D](https://box2d.org/) motor biztosítja
- **Service** használata zenelejátszáshoz

## Fontosabb technológiai megoldások

**A számodra legnehezebb/legérdekesebb funkciót fejtsd ki kb.  10 mondatban, hogy mi volt a probléma és hogyan oldottad meg.**