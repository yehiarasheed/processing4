<img src="https://processing.org/favicon.svg" width="250">

Processing is a flexible software sketchbook and a programming language designed for learning how to code.

This repository contains the source code for the [Processing](https://processing.org/) project for people who want to help improve the code.

## Using Processing

If you're interested in *using* Processing, head over to the [download page](https://processing.org/download), or read more about the project on the [Processing website](https://processing.org/). There are also several [tutorials](https://processing.org/tutorials) that provide a helpful introduction. They are complemented by hundreds of examples that are included with the software itself.

## Getting Help 
For assistance with your own sketches, projects, or code, please post your question on the Processing forum: https://discourse.processing.org/. Our community is full of experienced developers and knowledgeable users who are eager to help. Before you post, please take a moment to read the [guidelines on asking questions](https://discourse.processing.org/t/guidelines-asking-questions/2147) to make sure you get the best possible help. We’re incredibly grateful for the support and knowledge shared by everyone on the forum over the years.

## Contributing to Processing
We welcome new contributors. The work on Processing 4.0 was done by a [tiny number of people](https://github.com/processing/processing4/graphs/contributors?from=2019-10-01&to=2022-08-09&type=c). Every contribution helps!

If you want to fix a bug that's been bothering you or want to give back to the project, you're in the right place!

To get started, clone this repository to your machine and build the code. For instructions, check out our guide on [How to Build Processing](build/README.md).

### Structure of the Repository
The Processing software consists of two main parts:

1. **The Core Library:** This includes essential components such as the graphics renderer, event handling, input/output (I/O) functions, and mathematical utilities.
2. **The Processing Development Environment (PDE):** This includes a text editor, a pre-processor, a contributions manager, and other tools to assist with writing and managing code.

### About the Processing 4.0 release

We've moved to a new repository for the 4.0 release so that we could cull a lot of the accumulated mess of the last 20 years. This made `git clone` (and most other `git` operations) a lot faster.

For a detailed list of changes relevant to developers working on this repository, and changes that may impact Library, Mode, or Tool development, please refer to [CHANGELOG.md](CHANGELOG.md). The full list of changes can be seen in [the release notes for each version](build/shared/revisions.md).

Processing 4 has [important updates](wiki/Changes-in-4.0) that prepare the platform for its future. Most significantly, this includes the move to Java 17 as well as major changes to the range of platforms we support (Apple Silicon! Raspberry Pi on 32- and 64-bit ARM!)

With any luck, many changes should be transparent to most users, in spite of how much is updated behind the scenes. More immediately visible changes include major work on the UI, including “themes” and the ability to change how sketches are named by default.

As with all releases, we did [everything possible](https://twitter.com/ben_fry/status/1426282574683516928) to avoid breaking API. However, there were still tweaks that we had to make. We tried to keep them minor. Our goal is stability, and keeping everyone's code running.

## Contact Information
For technical support or troubleshooting with your project, please post on the [Processing Forum](https://discourse.processing.org/).

For bug reports or feature requests, please [create an issue](https://github.com/processing/processing4/issues).

For non-technical inquiries, here’s how to get in touch:

- For press inquiries, general information about the Processing software, or other non-technical questions, contact [hello@processing.org](mailto:hello@processing.org).
- For anything related to the Processing Foundation or broader topics beyond the software, please reach out to [foundation@processingfoundation.org](mailto:foundation@processingfoundation.org).

---

This version now includes guidance for bug reports and feature requests. How does this look?

## License & Copyright

- The **core library** is licensed under the GNU Lesser General Public License version 2.1 ([LGPL-2.1](https://www.gnu.org/licenses/old-licenses/lgpl-2.1.en.html)).
- Everything else including the **PDE** is licensed under the GNU General Public License version 2 ([GPL-2.0](https://www.gnu.org/licenses/old-licenses/gpl-2.0.html)).
- The **reference**, including the JavaDoc comments, is licensed under the Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License ([CC-BY-NC-SA-4.0](https://creativecommons.org/licenses/by-nc-sa/4.0/)).

For complete licensing information about the Processing core library and software, see [LICENSE.md](LICENSE.md)

For licensing information about the Processing website see the [processing-website README](https://github.com/processing/processing-website/blob/main/README.md#licenses).

Copyright (c) 2015-now The Processing Foundation
