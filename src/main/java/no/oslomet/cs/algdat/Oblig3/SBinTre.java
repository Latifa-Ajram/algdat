package no.oslomet.cs.algdat.Oblig3;


import java.util.*;

public class SBinTre<T> {
    private static final class Node<T>   // en indre nodeklasse
    {
        private T verdi;                   // nodens verdi
        private Node<T> venstre, høyre;    // venstre og høyre barn
        private Node<T> forelder;          // forelder

        // konstruktør
        private Node(T verdi, Node<T> v, Node<T> h, Node<T> forelder) {
            this.verdi = verdi;
            venstre = v;
            høyre = h;
            this.forelder = forelder;
        }

        private Node(T verdi, Node<T> forelder)  // konstruktør
        {
            this(verdi, null, null, forelder);
        }

        @Override
        public String toString() {
            return "" + verdi;
        }

    } // class Node

    private Node<T> rot;                            // peker til rotnoden
    private int antall;                             // antall noder
    private int endringer;                          // antall endringer

    private final Comparator<? super T> comp;       // komparator

    public SBinTre(Comparator<? super T> c)    // konstruktør
    {
        rot = null;
        antall = 0;
        comp = c;
    }

    public boolean inneholder(T verdi) {
        if (verdi == null) return false;// ingen null verdier

        Node<T> p = rot;

        while (p != null) {
            int cmp = comp.compare(verdi, p.verdi);// sammenligner
            if (cmp < 0) p = p.venstre; // går til venstre barn
            else if (cmp > 0) p = p.høyre;// går til høyre barn
            else return true; // verdien er funnet
        }

        return false; // verdien ikke funnet
    }

    public int antall() {
        return antall;
    }

    public String toStringPostOrder() {
        if (tom()) return "[]";

        StringJoiner s = new StringJoiner(", ", "[", "]");

        Node<T> p = førstePostorden(rot); // går til den første i postorden
        while (p != null) {
            s.add(p.verdi.toString());
            p = nestePostorden(p);
        }

        return s.toString();
    }

    public boolean tom() {
        return antall == 0;
    }

    public boolean leggInn(T verdi)
    {
        Objects.requireNonNull(verdi, "Ulovlig med nullverdier!");

        Node<T> p = rot, q = null;               // p starter i roten
        int cmp = 0;                             // hjelpevariabel

        while (p != null)       // fortsetter til p er ute av treet
        {
            q = p;                                 // q er forelder til p
            cmp = comp.compare(verdi,p.verdi);     // bruker komparatoren
            p = cmp < 0 ? p.venstre : p.høyre;// flytter p

        }

        // p er nå null, dvs. ute av treet, q er den siste vi passerte


        p = new Node<>(verdi,null);                   // oppretter en ny node

        if (q == null) rot = p;                  // p blir rotnode
        else if (cmp < 0) q.venstre = p;         // venstre barn til q
        else q.høyre = p;// høyre barn til q

        if(q != null){
            p.forelder = q;
        }else {
            p.forelder = null;
        }

        antall++;                                // én verdi mer i treet
        endringer++;
        return true;                             // vellykket innlegging
    }



    public boolean fjern(T verdi) {

        //throw new UnsupportedOperationException("Ikke kodet ennå!");
        if (verdi == null) return false;  // treet har ingen nullverdier

        Node<T> p = rot, q = null;   // q skal være forelder til p

        while (p != null)            // leter etter verdi
        {
            int cmp = comp.compare(verdi,p.verdi);      // sammenligner
            if (cmp < 0) { q = p; p = p.venstre; }      // går til venstre
            else if (cmp > 0) { q = p; p = p.høyre; }   // går til høyre
            else break;    // den søkte verdien ligger i p
        }
        if (p == null) return false;   // finner ikke verdi

        if (p.venstre == null || p.høyre == null)  // Tilfelle 1)bladnode og 2)p har nøyaktig ett barn
        {
            Node<T> b = p.venstre != null ? p.venstre : p.høyre;  // b for barn
            if (p == rot) rot = b;
            else if (p == q.venstre) q.venstre = b;
            else q.høyre = b;
            if(b != null)    b.forelder = q;// Nå får pekeren forelder korrekt verdi

        }
        else  // Tilfelle 3) p har to barn
        {
            Node<T> s = p, r = p.høyre;   // finner neste i inorden
            while (r.venstre != null)
            {
                s = r;    // s er forelder til r
                r = r.venstre;
            }

            p.verdi = r.verdi;   // kopierer verdien i r til p

            if (s != p) s.venstre = r.høyre;
            else s.høyre = r.høyre;
            if(r.høyre != null) r.høyre.forelder = s;// Oppdaterer peker forelder
        }

        antall--;   // det er nå én node mindre i treet
        endringer++;
        return true;
    }

    public int fjernAlle(T verdi) {
        //throw new UnsupportedOperationException("Ikke kodet ennå!");

        int antall = 0;
        while (fjern(verdi))antall++;
        return antall;

    }

    public int antall(T verdi) {
        //throw new UnsupportedOperationException("Ikke kodet ennå!");

     Node<T> p = rot;

     if (verdi == null) return 0;
     int n=0;
     while ( p != null){

         int cmp  = comp.compare(verdi,p.verdi);
         if(cmp < 0) p=p.venstre;
         else {
             if(cmp == 0)n++;
             p = p.høyre;
         }
     }
     return n;
    }



    public void nullstill() {
       // throw new UnsupportedOperationException("Ikke kodet ennå!");


        if(antall == 0)return;
        Node<T> p= rot; // rot initialisering


        p=førstePostorden(p);
        while (antall != 0){

            if(p != null) {
                fjern(p.verdi);
                p.verdi = null;
                p=nestePostorden(p);
            }

            antall--;
        }

    }

    private static <T> Node<T> førstePostorden(Node<T> p) {
        //throw new UnsupportedOperationException("Ikke kodet ennå!");

        while (true)
        {
            if (p.venstre != null) p = p.venstre;
            else if (p.høyre != null) p = p.høyre;
            else return p;
        }

    }

    private static <T> Node<T> nestePostorden(Node<T> p) {
        //throw new UnsupportedOperationException("Ikke kodet ennå!");

        Node<T> q= p.forelder; // initialisering av p.forelder

        if( q == null) return null;// hvis forelder er null så har vi kommet til siste node i treet(rot)

        if(q.høyre == p || q.høyre== null) return q;// det er forelder som returneres hvis ingen høyre barn eller hvis hvis høyre barn er p

        else return førstePostorden(q.høyre);// kaller på førstePostorden
    }

    public void postorden(Oppgave<? super T> oppgave) {
       // throw new UnsupportedOperationException("Ikke kodet ennå!");

        Node<T> p= rot;
       Node<T> q =  førstePostorden(p);// dette er første node i postorden traversering
        oppgave.utførOppgave(q.verdi);
        while (q.forelder != null){ // så lenge den første noden har en forelder skal neste i postorden traversering hentes

            q=nestePostorden(q);// kall på metoden for å hente neste.
            oppgave.utførOppgave(q.verdi);

        }
    }

    public void postordenRecursive(Oppgave<? super T> oppgave) {
        postordenRecursive(rot, oppgave);
    }

    private void postordenRecursive(Node<T> p, Oppgave<? super T> oppgave) {
       // throw new UnsupportedOperationException("Ikke kodet ennå!");

        if(p.venstre != null)postordenRecursive(p.venstre,oppgave);
        if(p.høyre != null) postordenRecursive(p.høyre,oppgave);
        oppgave.utførOppgave(p.verdi);
    }

    public ArrayList<T> serialize() {
        //throw new UnsupportedOperationException("Ikke kodet ennå!");
        ArrayList<T>a= new ArrayList<>();

        Node<T> p = rot;
        ArrayDeque<Node<T>> kø= new ArrayDeque<>();
        kø.addLast(p);// rotnoden legges i køen
        while (!kø.isEmpty()){

            Node<T> q= kø.removeFirst();// første tas ut fra køen
            if(q.venstre != null) kø.addLast(q.venstre);// legg til nodens venstre barn i køen
            if(q.høyre != null)kø.addLast(q.høyre); // legg til nodens høyre barn i køen

            a.add(q.verdi);// verdien legges i arrayet

        }
        return a;

    }

    static <K> SBinTre<K> deserialize(ArrayList<K> data, Comparator<? super K> c) {
      //  throw new UnsupportedOperationException("Ikke kodet ennå!");

     SBinTre<K> tre = new SBinTre<K>(c);// initialisering ev et binærtre

     for (K verdi :data){ // går gjennom alle verdier i arraylist

         tre.leggInn(verdi);// legger verdi i treet
      }
     return tre;

    }


} // ObligSBinTre
