﻿using System;

namespace SuperSimonEmulator.Commands.Concrete
{
    public class GameCompletedCommand : PayloadCommand
    {
        public GameCompletedCommand() : base(5) { } // 0000 0101

        public GameCompletedCommand(byte targetAddress)
            : base(5)
        {
            TargetAddress = targetAddress;
        }

        public void AddTiming(byte buttonId, short timeToPressMs)
        {
            AppendToPayload(buttonId);
            byte[] data = BitConverter.GetBytes(timeToPressMs);
            if (BitConverter.IsLittleEndian)
                Array.Reverse(data);
            AppendToPayload(data);
        }
    }
}