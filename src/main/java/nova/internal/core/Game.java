package nova.internal.core;

import nova.core.block.BlockManager;
import nova.core.component.ComponentManager;
import nova.core.entity.EntityManager;
import nova.core.event.GlobalEvents;
import nova.core.game.ClientManager;
import nova.core.game.GameInfo;
import nova.core.game.InputManager;
import nova.core.item.ItemDictionary;
import nova.core.item.ItemManager;
import nova.core.nativewrapper.NativeManager;
import nova.core.network.NetworkManager;
import nova.core.recipes.RecipeManager;
import nova.core.recipes.crafting.CraftingRecipeManager;
import nova.core.render.RenderManager;
import nova.core.util.LanguageManager;
import nova.core.util.RetentionManager;
import nova.core.world.WorldManager;
import nova.internal.core.bootstrap.DependencyInjectionEntryPoint;
import nova.internal.core.tick.UpdateTicker;
import org.slf4j.Logger;
import se.jbee.inject.Injector;

public class Game {

	private static Game instance;
	private static Injector injector;

	private final Logger logger;

	private final GameInfo gameInfo;
	private final ClientManager clientManager;
	private final BlockManager blockManager;
	private final EntityManager entityManager;
	private final ItemManager itemManager;
	private final WorldManager worldManager;
	private final RenderManager renderManager;
	private final RecipeManager recipeManager;
	private final CraftingRecipeManager craftingRecipeManager;
	private final ItemDictionary itemDictionary;
	private final GlobalEvents eventManager;
	private final NetworkManager networkManager;
	private final RetentionManager retentionManager;
	private final LanguageManager languageManager;
	private final InputManager inputManager;
	private final ComponentManager componentManager;
	private final NativeManager nativeManager;

	/**
	 * The synchronized ticker that uses the same thread as the game.
	 *
	 * This is @deprecated, use syncTicker() instead.
	 */
	private final UpdateTicker.SynchronizedTicker syncTicker;

	/**
	 * The thread ticker that runs on NOVA's thread.
	 *
	 * This is @deprecated, use threadTicker() instead.
	 */
	private final UpdateTicker.ThreadTicker threadTicker;

	private Game(
		Logger logger,
		GameInfo gameInfo,
		ClientManager clientManager,
		BlockManager blockManager,
		EntityManager entityManager,
		ItemManager itemManager,
		WorldManager worldManager,
		RenderManager renderManager,
		RecipeManager recipeManager,
		CraftingRecipeManager craftingRecipeManager,
		ItemDictionary itemDictionary,
		GlobalEvents eventManager,
		NetworkManager networkManager,
		RetentionManager retentionManager,
		LanguageManager languageManager,
		InputManager inputManager,
		NativeManager nativeManager,
		ComponentManager componentManager,
		UpdateTicker.SynchronizedTicker syncTicker,
		UpdateTicker.ThreadTicker threadTicker) {

		this.logger = logger;

		this.gameInfo = gameInfo;
		this.clientManager = clientManager;
		this.blockManager = blockManager;
		this.entityManager = entityManager;
		this.itemManager = itemManager;
		this.worldManager = worldManager;
		this.renderManager = renderManager;
		this.recipeManager = recipeManager;
		this.craftingRecipeManager = craftingRecipeManager;
		this.itemDictionary = itemDictionary;
		this.eventManager = eventManager;
		this.networkManager = networkManager;
		this.retentionManager = retentionManager;
		this.languageManager = languageManager;
		this.inputManager = inputManager;
		this.nativeManager = nativeManager;
		this.componentManager = componentManager;

		this.syncTicker = syncTicker;
		this.threadTicker = threadTicker;

		logger.info("Game instance created.");
	}

	public static void inject(DependencyInjectionEntryPoint diep) {
		instance = diep.init();
		injector = diep.getInjector().get();
	}

	public static GameInfo info() {
		return instance.gameInfo;
	}

	public static Injector injector() {
		return injector;
	}

	public static Logger logger() {
		return instance.logger;
	}

	public static ClientManager clientManager() {
		return instance.clientManager;
	}

	public static BlockManager blocks() {
		return instance.blockManager;
	}

	public static EntityManager entities() {
		return instance.entityManager;
	}

	public static ItemManager items() {
		return instance.itemManager;
	}

	public static WorldManager worlds() {
		return instance.worldManager;
	}

	public static RenderManager render() {
		return instance.renderManager;
	}

	public static RecipeManager recipes() {
		return instance.recipeManager;
	}

	public static CraftingRecipeManager craftingRecipes() {
		return instance.craftingRecipeManager;
	}

	public static ItemDictionary itemDictionary() {
		return instance.itemDictionary;
	}

	public static GlobalEvents events() {
		return instance.eventManager;
	}

	public static NetworkManager network() {
		return instance.networkManager;
	}

	public static RetentionManager retention() {
		return instance.retentionManager;
	}

	public static LanguageManager language() {
		return instance.languageManager;
	}

	public static InputManager input() {
		return instance.inputManager;
	}

	public static ComponentManager components() {
		return instance.componentManager;
	}

	public static NativeManager natives() {
		return instance.nativeManager;
	}

	/**
	 * The synchronized ticker that uses the same thread as the game.
	 */
	public static UpdateTicker.SynchronizedTicker syncTicker() {
		return instance.syncTicker;
	}

	/**
	 * The thread ticker that runs on NOVA's thread.
	 */
	public static UpdateTicker.ThreadTicker threadTicker() {
		return instance.threadTicker;
	}
}
