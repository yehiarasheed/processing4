# Processing 32-bit Linux

For the Raspberry Pi 3 and older we'd need to create an 32-bit arm version of
processing.

Github Actions do not support creating 32 bit runners so we need to host one
ourselves.

## Instructions

Use [Raspberry Pi Imager](https://www.raspberrypi.com/software/) to download
Raspberry PI OS lite (32bit) to an SD card

Use the settings to set up a proper hostname and user account.

Take the written SD card and put it into the Raspberry PI

Power it up and wait for it to boot

SSH into the Raspberry PI

Follow Githubs instructions on how to set up a self-hosted runner
https://docs.github.com/en/actions/hosting-your-own-runners/managing-self-hosted-runners/adding-self-hosted-runners

Use all the default values in the `./config.sh` setup step

Use Github instructions to setup the runner as a services so it runs on boot
https://docs.github.com/en/actions/hosting-your-own-runners/managing-self-hosted-runners/configuring-the-self-hosted-runner-application-as-a-service

Done.
