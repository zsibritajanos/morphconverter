package converter;

public class UnivMorph2MSDFeat {

    /**
     *
     */
    public class ConllResult {
        String lemma;
        String pos;
        String feat;

        public ConllResult(String l, String p, String f) {
            lemma = l;
            pos = p;
            feat = f;
        }

        public String toString() {
            return this.lemma + "\t" + this.pos + "\t" + this.feat;
        }
    }

    /**
     *
     */
    public UnivMorph2MSDFeat() {

    }

    /**
     * @param features
     * @return
     */
    private String replaceFeatures(String features) {

        String converted = features;

        //nominal features
        //number
        converted = converted.replace("Number=Sing", "Num=s");
        converted = converted.replace("Number=Plur", "Num=p");
        converted = converted.replace("Number=None", "Num=none");

        //definite (conjugation too)
        converted = converted.replace("Definite=Def", "Def=y");
        converted = converted.replace("Definite=Ind", "Def=n");
        converted = converted.replace("Definite=2", "Def=2");

        //degree
        converted = converted.replace("Degree=None", "Deg=none");
        converted = converted.replace("Degree=Pos", "Deg=p");
        converted = converted.replace("Degree=Cmp", "Deg=c");
        converted = converted.replace("Degree=Sup", "Deg=s");
        converted = converted.replace("Degree=Abs", "Deg=e");

        //case
        converted = converted.replace("Case=Nom|", "Cas=n|");
        converted = converted.replace("Case=Acc", "Cas=a");
        converted = converted.replace("Case=Dat", "Cas=d");
        converted = converted.replace("Case=Gen", "Cas=g");
        converted = converted.replace("Case=Ins", "Cas=i");
        converted = converted.replace("Case=Ill", "Cas=x");
        converted = converted.replace("Case=Ine", "Cas=2");
        converted = converted.replace("Case=Ela", "Cas=e");
        converted = converted.replace("Case=All", "Cas=t");
        converted = converted.replace("Case=Ade", "Cas=3");
        converted = converted.replace("Case=Abl", "Cas=b");
        converted = converted.replace("Case=Sub", "Cas=s");
        converted = converted.replace("Case=Sup", "Cas=p");
        converted = converted.replace("Case=Del", "Cas=h");
        converted = converted.replace("Case=Ter", "Cas=9");
        //essive???
        converted = converted.replace("Case=Ess", "Cas=w");
        converted = converted.replace("Case=Abs", "Cas=f"); //!!!!!
        converted = converted.replace("Case=Tem", "Cas=m");
        converted = converted.replace("Case=Cau", "Cas=c");
        converted = converted.replace("Case=Com", "Cas=q");
        converted = converted.replace("Case=Tra", "Cas=y");
        converted = converted.replace("Case=Dis", "Cas=u");
        converted = converted.replace("Case=Loc", "Cas=l");
        converted = converted.replace("Case=None", "Cas=none");

        //possession
        converted = converted.replace("Number[psor]=Sing", "NumP=s");
        converted = converted.replace("Number[psor]=Plur", "NumP=p");
        converted = converted.replace("Number[psor]=None", "NumP=none");
        converted = converted.replace("Person[psor]=1", "PerP=1");
        converted = converted.replace("Person[psor]=2", "PerP=2");
        converted = converted.replace("Person[psor]=3", "PerP=3");
        converted = converted.replace("Person[psor]=None", "PerP=none");
        converted = converted.replace("Number[psed]=Sing", "NumPd=s");
        converted = converted.replace("Number[psed]=Plur", "NumPd=p");
        converted = converted.replace("Number[psed]=None", "NumPd=none");

        //verbal features
        //verbform
        converted = converted.replace("VerbForm=PartPres", "SubPOS=p");
        converted = converted.replace("VerbForm=PartPast", "SubPOS=s");
        converted = converted.replace("VerbForm=PartFut", "SubPOS=u");

        //mood
        converted = converted.replace("Mood=Ind", "Mood=i");
        converted = converted.replace("Mood=Cnd", "Mood=c");
        converted = converted.replace("Mood=Imp", "Mood=m");
        converted = converted.replace("Mood=Cnd,Pot", "Mood=c");
        converted = converted.replace("Mood=Imp,Pot", "Mood=m");

        //tense
        converted = converted.replace("Tense=Pres", "Tense=p");
        converted = converted.replace("Tense=Past", "Tense=s");

        //person
        converted = converted.replace("Person=1", "Per=1");
        converted = converted.replace("Person=2", "Per=2");
        converted = converted.replace("Person=3", "Per=3");
        converted = converted.replace("Person=none", "Per=None");

        //lexical features
        //prontype
        //Rp separately
        converted = converted.replace("SubPOS=Prs", "SubPOS=p");
        converted = converted.replace("SubPOS=Dem", "SubPOS=d");
        converted = converted.replace("SubPOS=Ind", "SubPOS=i");
        converted = converted.replace("SubPOS=Prs|Poss=Yes", "SubPOS=s");
        converted = converted.replace("SubPOS=Int", "SubPOS=q");
        converted = converted.replace("SubPOS=Rel", "SubPOS=r");
        converted = converted.replace("SubPOS=Rcp", "SubPOS=y");
        converted = converted.replace("SubPOS=Tot", "SubPOS=g");
        converted = converted.replace("Reflex=Yes", "SubPOS=x");
        converted = converted.replace("SubPOS=Neg", "SubPOS=m");
        converted = converted.replace("VerbForm=Trans", "SubPOS=v");

        return converted;
    }

    public ConllResult convert(String lemma, String pos, String feature) {

        //kell-e???
        //Twitter-specific annotation
        if (pos.equals("E")) {
            pos = "SYM";
            feature = "_";
        }

        //interjections
        if (pos.equals("INTJ")) {
            pos = "I";
            feature = "_";
        }

        //punctuations
        if (pos.equals("PUNCT")) {
            feature = "_";
            if (lemma.equals(",")) {
                pos = ",";
            } else if (lemma.equals(";")) {
                pos = ";";
            } else if (lemma.equals("?")) {
                pos = "?";
            } else if (lemma.equals(".")) {
                pos = ".";
            } else if (lemma.equals(":")) {
                pos = ":";
            } else if (lemma.equals("-")) {
                pos = "-";
            } else if (lemma.equals("–")) {
                pos = "–";
            } else if (lemma.equals("!")) {
                pos = "!";
            } else
                pos = "K";
        }

        //conjunctions
        if (pos.equals("CONJ")) {
            pos = "C";
            feature = "SubPOS=c";
        }

        if (pos.equals("SCONJ")) {
            pos = "C";
            feature = "SubPOS=s";
        }

        //articles
        if (pos.equals("DET") && feature.contains("Definite=Def")) {
            pos = "T";
            feature = "SubPOS=f";
        }

        if (pos.equals("DET") && feature.contains("Definite=Ind")) {
            pos = "T";
            feature = "SubPOS=i";
        }

        //postpositions
        if (pos.equals("ADP")) {
            pos = "S";
            feature = "SubPOS=t";
        }

        //particles
        if (pos.equals("PART")) {
            pos = "R";
            feature = "SubPOS=p";
        }

        //adverbs
        if (pos.equals("ADV")) {
            pos = "R";
            if (feature.contains("PronType")) {
                feature = feature.replace("PronType", "SubPOS");
            } else {
                feature = ("SubPOS=x");
            }
        }

        //pronouns
        if (pos.equals("PRON")) {
            pos = "P";
            feature = feature.replace("PronType", "SubPOS");
        }

        //nouns
        if (pos.equals("NOUN")) {
            pos = "N";
            feature = feature.replace("Cas", "SubPOS=c|Cas");
        }

        if (pos.equals("PROPN")) {
            pos = "N";
            feature = feature.replace("Cas", "SubPOS=p|Cas");
        }

        //adjectives
        if (pos.equals("ADJ")) {
            pos = "A";
            feature = feature.replace("|NumType=None", "");
            if (feature.contains("VerbForm=None")) {
                feature = feature.replace("VerbForm=None", "SubPOS=f");

            }
        }

        //auxiliaries
        if (pos.equals("AUX")) {
            pos = "V";
            feature = feature.replace("Voice=Act", "SubPOS=a");
            feature = feature.replace("|Aspect=None", "");
        }

        //more aux to come from dep analysis

        //verbs
        if (pos.equals("VERB")) {
            pos = "V";

            if (feature.contains("VerbForm=Fin") && feature.contains("Voice=Act")) {
                feature = feature.replace("VerbForm=Fin", "SubPOS=m");
                feature = feature.replace("|Voice=Act", "");
            }

            //caus voice
            if (feature.contains("VerbForm=Fin") && feature.contains("Voice=Cau")) {
                feature = feature.replace("VerbForm=Fin", "SubPOS=s");
                feature = feature.replace("|Voice=Cau", "");
            }

            //modal aspect
            if (feature.contains("VerbForm=Fin") && feature.contains("Voice=Act")
                    && feature.contains("Mood=Pot|")) {
                feature = feature.replace("VerbForm=Fin", "SubPOS=o");
                feature = feature.replace("|Voice=Act", "");
                feature = feature.replace("Mood=Pot|", "Mood=i|");
            }

            //freq aspect
            if (feature.contains("VerbForm=Fin") && feature.contains("Voice=Act") && feature.contains("Aspect=Freq")) {
                feature = feature.replace("VerbForm=Fin", "SubPOS=f");
                feature = feature.replace("|Voice=Act", "");
                feature = feature.replace("Aspect=Freq|", "");
            }

            //freq+modal
            if (feature.contains("VerbForm=Fin") && feature.contains("Voice=Act") && feature.contains("Aspect=Freq")
                    && feature.contains("Mood=Pot")) {
                feature = feature.replace("VerbForm=Fin", "SubPOS=1");
                feature = feature.replace("|Voice=Act", "");
                feature = feature.replace("Aspect=Freq|", "");
                feature = feature.replace("Mood=Pot", "Mood=");
            }

            //caus+modal
            if (feature.contains("VerbForm=Fin") && feature.contains("Voice=Cau")
                    && feature.contains("Mood=Pot")) {
                feature = feature.replace("VerbForm=Fin", "SubPOS=2");
                feature = feature.replace("|Voice=Act", "");
                feature = feature.replace("Aspect=Freq|", "");
                feature = feature.replace("Mood=Pot", "Mood=");
            }

            //caus+freq
            if (feature.contains("VerbForm=Fin") && feature.contains("Voice=Cau") && feature.contains("Aspect=Freq")) {
                feature = feature.replace("VerbForm=Fin", "SubPOS=3");
                feature = feature.replace("|Voice=Act", "");
                feature = feature.replace("Aspect=Freq|", "");
            }
            //caus+modal+freq
            if (feature.contains("VerbForm=Fin") && feature.contains("Voice=Cau") && feature.contains("Aspect=Freq")
                    && feature.contains("Mood=Pot")) {
                feature = feature.replace("VerbForm=Fin", "SubPOS=4");
                feature = feature.replace("|Voice=Act", "");
                feature = feature.replace("Aspect=Freq|", "");
                feature = feature.replace("Mood=Pot", "Mood=");
            }

            //infinitive
            if (feature.contains("VerbForm=Inf")) {
                feature = feature.replace("VerbForm=Inf", "Mood=n");
            }

            //potential
            if (feature.contains("Mood=Pot|")) {
                feature = feature.replace("Mood=Pot|", "");
                feature = feature.replace("SubPOS=m", "SubPOS=o");
            }

        }

        //cardinal and fraction numbers
        if (pos.equals("NUM") && feature.contains("NumType=Card")) {
            pos = "M";
            feature = feature.replace("NumType=Card", "SubPOS=c");
        }

        if (pos.equals("NUM") && feature.contains("NumType=Frac")) {
            pos = "M";
            feature = feature.replace("NumType=Frac", "SubPOS=f");
        }

        if (pos.equals("NUM") && feature.contains("NumType=Dist")) {
            pos = "M";
            feature = feature.replace("NumType=Dist", "SubPOS=d");
        }

        String replacedFeatures = replaceFeatures(feature);

        return new ConllResult(lemma, pos, replacedFeatures);
    }


    public static void main(String[] args) {
        System.out.println(new UnivMorph2MSDFeat().convert("Olaszország", "PROPN", "Case=Nom|Number=Sing"));
    }
}
