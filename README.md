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

I oppgave 4 
Del 1: Iterativ postorden uten bruk av hjelpestakk. Jeg har først definert rot, så kalte jeg på
metoden førstepostorden() for å hente den første noden i postorden traversering.
og brukte en while løkke for å legge til nye noder ved kall på nestepostorden(). løkken foregår
så lenge den første i postorden har en forelder

Del 2: Jeg kaller postordenrekursive  første gang for å besøke venstre barn, basis tilfelle er når 
venstre barn er lik null. så kaller jeg igjen metoden for å besøke høyre barn,basis tilfelle er når 
høyre barn er null, til slutt besøker jeg noden (som er første verdi i postorden traversering)

I oppgave 5 
Del 1: Jeg startet med initialisering av et arraylist. og p som rotnode.
deretter trengte jeg å initialisere en kø(jeg brukte type ArrayDeque).så lenge køen ikke er tom skjer dette:
Roten legges inn i køen, så tas ut den første i køen, nodens venstre og høyre barn legges i køen, verdien til noden legges inn i arrayet.

Del 2: jeg startet med å initialisere treet, så lopper jeg gjnnemon verdiene i arrayet ved bruk av
for alle løkke , og legger hver eneste verdi i treet ved bruk av legg inn metoden.
til slutt returneres treet.

I oppgave 6
Del 1 : fjern(T verdi), i tilfellene 1 og 2 ,skal pekeren q som er satt som forelder til p
bli forelder til barnet til p etter at p fjernes.
i tilfelle 3) så skal forelder(s) til noden som kommer nest  i innorden(r) etter noden vi vil fjerne  bli forlder til
r.høyre. se kode.

Del 2: fjernAlle(T verdi) jeg har startet med åinitialisere antlall verdier som skal fjernes.
i en while løkke fjrener jeg verdiene ved kall på fjern metoden, antall økes for en hver fjerning som skjer, så returneres 
antallet etter å ha fjernet alle veridier som tilsvarer input i metoden fjern(T verdi)

Del 3: nullstill() her har jeg funnet først den første noden i postorden,ved bruk av metoden
førstPostorden, og så lenge antall noder i treet ikke er lik 0 skjer dette:  bruk fjern(T verdi)metoden
for å fjerne verdi, så settes den til null, og videre til neste node ved kall på nestePostorden. ved hver iterasjon reduseres antall noder.

