package net.mcreator.scriptor.procedures;

import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.CapabilityItemHandler;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.inventory.container.Slot;
import net.minecraft.inventory.container.Container;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.block.Blocks;

import net.mcreator.scriptor.ScriptorModElements;
import net.mcreator.scriptor.ScriptorMod;

import java.util.function.Supplier;
import java.util.Map;

@ScriptorModElements.ModElement.Tag
public class ExchangeButtonProcedure extends ScriptorModElements.ModElement {
	public ExchangeButtonProcedure(ScriptorModElements instance) {
		super(instance, 7);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				ScriptorMod.LOGGER.warn("Failed to load dependency entity for procedure ExchangeButton!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				ScriptorMod.LOGGER.warn("Failed to load dependency x for procedure ExchangeButton!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				ScriptorMod.LOGGER.warn("Failed to load dependency y for procedure ExchangeButton!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				ScriptorMod.LOGGER.warn("Failed to load dependency z for procedure ExchangeButton!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				ScriptorMod.LOGGER.warn("Failed to load dependency world for procedure ExchangeButton!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		{
			TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
			if (_ent != null) {
				final int _sltid = (int) (1);
				final int _amount = (int) 1;
				_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
					if (capability instanceof IItemHandlerModifiable) {
						ItemStack _stk = capability.getStackInSlot(_sltid).copy();
						_stk.shrink(_amount);
						((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _stk);
					}
				});
			}
		}
		if (entity instanceof PlayerEntity) {
			Container _current = ((PlayerEntity) entity).openContainer;
			if (_current instanceof Supplier) {
				Object invobj = ((Supplier) _current).get();
				if (invobj instanceof Map) {
					ItemStack _setstack = new ItemStack(Blocks.DIAMOND_BLOCK, (int) (1));
					_setstack.setCount((int) 999);
					((Slot) ((Map) invobj).get((int) (2))).putStack(_setstack);
					_current.detectAndSendChanges();
				}
			}
		}
	}
}
