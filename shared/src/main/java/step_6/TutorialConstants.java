package step_6;

public class TutorialConstants {
    public static final String PERSON_MODEL_ID = unique("modelId");
    public static final String FIRSTNAME_ID = "attrId";
    public static final String COMMAND_ID = unique("LogOnServer");

    private static String unique(String key) {
        return TutorialConstants.class.getName() + "." + key;
    }

}
