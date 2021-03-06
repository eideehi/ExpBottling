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

package net.eidee.minecraft.exp_bottling.network.message;

import java.util.function.Supplier;
import javax.annotation.ParametersAreNonnullByDefault;

import mcp.MethodsReturnNonnullByDefault;
import net.eidee.minecraft.exp_bottling.inventory.container.ExpBottlingMachineContainer;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class SetBottlingExp
{
    private final int expValue;

    public SetBottlingExp( int expValue )
    {
        this.expValue = expValue;
    }

    public int getExpValue()
    {
        return expValue;
    }

    public static void encode( SetBottlingExp message, PacketBuffer buffer )
    {
        buffer.writeInt( message.expValue );
    }

    public static SetBottlingExp decode( PacketBuffer buffer )
    {
        return new SetBottlingExp( buffer.readInt() );
    }

    public static void handle( SetBottlingExp message, Supplier< NetworkEvent.Context > ctx )
    {
        NetworkEvent.Context _ctx = ctx.get();
        _ctx.enqueueWork( () -> {
            ServerPlayerEntity player = _ctx.getSender();
            if ( player != null && player.openContainer instanceof ExpBottlingMachineContainer )
            {
                ( ( ExpBottlingMachineContainer )player.openContainer ).setBottlingExp( message.getExpValue() );
            }
        } );
        _ctx.setPacketHandled( true );
    }
}
