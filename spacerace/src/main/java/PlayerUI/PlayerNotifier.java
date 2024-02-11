package PlayerUI;

/**
 * playerFactory used to create notEnoughFounds
 * @author anton byström
 */
public class PlayerNotifier implements PlayerFactory<Notifier> {

    /**
     * checks what gui type to create
     * @param guiType type of gui to create
     * @return a new guiType or null
     */
    @Override
    public Notifier create(String guiType) {
        if (guiType == null || guiType.isEmpty()) {
            return null;
        }
        if (guiType.contains("notEnoughFounds")) {
            return new NotEnoughFounds(guiType);
        }
        return null;
    }
}
