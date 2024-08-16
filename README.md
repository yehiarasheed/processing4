<img src="https://processing.org/favicon.svg" width="250">

Processing is a flexible software sketchbook and a programming language designed for learning how to code.

This repository contains the source code for the [Processing](https://processing.org/) project for people who want to help improve the code. It consists of two main parts:

1. **The Core Library:** This includes essential components such as the graphics renderer, event handling, input/output (I/O) functions, and mathematical utilities.
2. **The Processing Development Environment (PDE):** This includes a text editor, a compiler, a contributions manager, and other tools to assist with writing and managing code.

## Using Processing

If you're interested in *using* Processing, get started at the [processing.org download page](https://processing.org/download), or read more about the project at the [home page](https://processing.org/). There are also several [tutorials](https://processing.org/tutorials) that provide a helpful introduction. They are complemented by hundreds of examples that are included with the software itself.

## Contributing to Processing

We welcome new contributors! The work on 4.0 was done by a [tiny number of people](https://github.com/processing/processing4/graphs/contributors?from=2019-10-01&to=2022-08-09&type=c). Every contribution helps.

If you want to fix a bug that's been bothering you or want to give back to the project, you're in the right place!

To get started, see the [instructions on how to build Processing on your machine](build/README.md).

### About the Processing 4.0 release

We've moved to a new repository for the 4.0 release so that we could cull a lot of the accumulated mess of the last 20 years. This made `git clone` (and most other `git` operations) a lot faster.

For a detailed list of changes relevant to developers working on this repository, and changes that may impact Library, Mode, or Tool development, please refer to [CHANGELOG.md](CHANGELOG.md). The full list of changes can be seen in [the release notes for each version](build/shared/revisions.md).

Processing 4 has [important updates](wiki/Changes-in-4.0) that prepare the platform for its future. Most significantly, this includes the move to Java 17 as well as major changes to the range of platforms we support (Apple Silicon! Raspberry Pi on 32- and 64-bit ARM!)

With any luck, many changes should be transparent to most users, in spite of how much is updated behind the scenes. More immediately visible changes include major work on the UI, including “themes” and the ability to change how sketches are named by default.

As with all releases, we did [everything possible](https://twitter.com/ben_fry/status/1426282574683516928) to avoid breaking API. However, there were still tweaks that we had to make. We tried to keep them minor. Our goal is stability, and keeping everyone's code running.
