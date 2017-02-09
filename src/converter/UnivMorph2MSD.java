package converter;

/**
 * Created by zsjanos on 2017.02.04..
 */
public class UnivMorph2MSD {

    private static UnivMorph2MSDFeat univMorph2MSDFeat = new UnivMorph2MSDFeat();
    private static CoNLLFeaturesToMSD coNLLFeaturesToMSD = new CoNLLFeaturesToMSD();

    /**
     * @return
     */
    public static String convert(String lemma, String pos, String feature) {
        UnivMorph2MSDFeat.ConllResult result = univMorph2MSDFeat.convert(lemma, pos, feature);
        return coNLLFeaturesToMSD.convert(result.pos, result.feat);
    }

    public static void main(String[] args) {
        System.out.println(convert("pozíció", "NOUN", "Case=Ade|Number=Sing"));
    }
}
