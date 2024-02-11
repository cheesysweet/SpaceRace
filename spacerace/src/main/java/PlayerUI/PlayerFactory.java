package PlayerUI;

/**
 * Interface used create ui
 * @param <T>
 */
public interface PlayerFactory<T> {
    T create(String player);
}
