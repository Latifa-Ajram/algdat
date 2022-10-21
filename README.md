# Obligatorisk oppgave 3 i Algoritmer og Datastrukturer

Denne oppgaven er en innlevering i Algoritmer og Datastrukturer. 
Oppgaven er levert av følgende student:
* Navn Latifa Ajram, S349520, s349520@oslomet.no


# Oppgavebeskrivelse

I oppgave 1 Oppdaterte jeg forelder til hver node som legges inn, ved å sjekke pekeren q
hvis den ikke er null, vil dette si at den nye nodens forleder er q ellers er forlederen til den nye noden
null, det betyr at vår nye node er roten i vårt binær søketre.

I oppgave 2 så brukte jeg en comparator for å sammenligne verdi (input) med alle treets verdier, dersom comprator returnerer verdi
mindre enn 0 ,betyr dette at pekeren flyttes til venstre barn, hvis retur verdien er 0, btyr dette at verdien er funnet,
og vår teller øker med 1, og pekeren flyttes til høyre barn. vi holder med dette helt til pekeren er null.
og til slutt returneres antall like verdier som stemmer med input.

I oppgave 3 startet jeg med å definere p sin forelder q, hvis den er null blir det ingen nestepostorden(det blir den første noden som skal returneres i postorden)
Hvis høyre barn til q(forelder) er p eller er null, da er det q som skal returneres.
ellers kaller vi metoden Postorden med q.høyere som parameter.

