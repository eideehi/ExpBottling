/*
 * MIT License
 *
 * Copyright (c) 2020 EideeHi
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package net.eidee.minecraft.exp_bottling.init;

import net.eidee.minecraft.exp_bottling.ExpBottlingMod;
import net.eidee.minecraft.exp_bottling.block.Blocks;
import net.eidee.minecraft.exp_bottling.block.ExpBottlingMachineBlock;
import net.eidee.minecraft.exp_bottling.constants.RegistryNames;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber( modid = ExpBottlingMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD )
public class BlockInitializer
{
    @SubscribeEvent
    public static void registerBlock( RegistryEvent.Register< Block > event )
    {
        IForgeRegistry< Block > registry = event.getRegistry();

        Block block;
        Block.Properties prop;
        {
            prop = Block.Properties.create( Material.IRON )
                                   .hardnessAndResistance( 3.0F )
                                   .doesNotBlockMovement();
            block = new ExpBottlingMachineBlock( prop ).setRegistryName( RegistryNames.EXP_BOTTLING_MACHINE );
            registry.register( block );
        }
    }

    @SubscribeEvent
    public static void registerItem( RegistryEvent.Register< Item > event )
    {
        IForgeRegistry< Item > registry = event.getRegistry();

        Item item;
        Item.Properties prop;
        {
            prop = new Item.Properties().group( ItemGroup.DECORATIONS );

            item = new BlockItem( Blocks.EXP_BOTTLING_MACHINE, prop ).setRegistryName( RegistryNames.EXP_BOTTLING_MACHINE );
            registry.register( item );
        }
    }

    @OnlyIn( Dist.CLIENT )
    public static void registerRenderType()
    {
        RenderTypeLookup.setRenderLayer( Blocks.EXP_BOTTLING_MACHINE, RenderType.getCutout() );
    }
}
