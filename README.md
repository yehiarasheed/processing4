<!-- Uncomment the shield below once the repo is made public -->
<!-- [![All Contributors](https://img.shields.io/github/all-contributors/processing/processing4?color=ee8449)](#contributors) -->

<img src="https://processing.org/favicon.svg" width="250">

Processing is a flexible software sketchbook and a programming language designed for learning how to code.

This repository contains the source code for the [Processing](https://processing.org/) project for people who want to help improve the code.

## Using Processing

If you're interested in *using* Processing, head over to the [download page](https://processing.org/download), or read more about the project on the [Processing website](https://processing.org/). There are also several [tutorials](https://processing.org/tutorials) that provide a helpful introduction. They are complemented by hundreds of examples that are included with the software itself.

## Getting Help 
For assistance with your own sketches, projects, or code, please post your question on the Processing forum: https://discourse.processing.org/. Our community is full of experienced developers and knowledgeable users who are eager to help. Before you post, please take a moment to read the [guidelines on asking questions](https://discourse.processing.org/t/guidelines-asking-questions/2147) to make sure you get the best possible help. We’re incredibly grateful for the support and knowledge shared by everyone on the forum over the years.

## Contributing to Processing
We welcome new contributors. If you want to fix a bug that's been bothering you or want to give back to the project, you're in the right place! Please see our [CONTRIBUTING.md](CONTRIBUTING.md) for detailed guidelines on how to contribute.

## Expected Behavior 
Remember, Processing is a labor of love, run in large part by volunteers, and offered free of charge. We're here because we believe in this community and genuinely enjoy contributing to it. We always welcome constructive feedback. Just keep it friendly and helpful, please! For more tips on how to communicate within the project, take a peek at our [Code of Conduct](https://github.com/processing/processing4-carbon-aug-19?tab=coc-ov-file).

## Building Processing

Building Processing locally on your machine will let you troubleshoot and make sure your contributions work as intended before submitting them to this repository. It also gives you the flexibility to experiment and learn more about how Processing is structured.

For a quick start: 
1. Fork and clone the repository.
1. Open it in IntelliJ IDEA.
1. Install the required [Ant plugin](https://plugins.jetbrains.com/plugin/23025-ant).
1. Hit Run.

For more information and detailed instructions, follow our [How to Build Processing](build/README.md) guide.

## About the Processing 4.0 release

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

## License & Copyright

- The **core library** is licensed under the GNU Lesser General Public License version 2.1 ([LGPL-2.1](https://www.gnu.org/licenses/old-licenses/lgpl-2.1.en.html)).
- Everything else including the **PDE** is licensed under the GNU General Public License version 2 ([GPL-2.0](https://www.gnu.org/licenses/old-licenses/gpl-2.0.html)).
- The **reference**, including the JavaDoc comments, is licensed under the Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License ([CC-BY-NC-SA-4.0](https://creativecommons.org/licenses/by-nc-sa/4.0/)).

For complete licensing information about the Processing core library and software, see [LICENSE.md](LICENSE.md)

For licensing information about the Processing website see the [processing-website README](https://github.com/processing/processing-website/blob/main/README.md#licenses).

Copyright (c) 2015-now The Processing Foundation

## Contributors

<!-- ALL-CONTRIBUTORS-LIST:START - Do not remove or modify this section -->
<!-- prettier-ignore-start -->
<!-- markdownlint-disable -->
<table>
  <tbody>
    <tr>
      <td align="center" valign="top" width="16.66%"><a href="https://fathom.info"><img src="https://avatars.githubusercontent.com/u/1623101?v=4?s=120" width="120px;" alt="Ben Fry"/><br /><sub><b>Ben Fry</b></sub></a><br /><a href="https://github.com/processing/processing4-carbon-aug-19/commits?author=benfry" title="Code">💻</a> <a href="#ideas-benfry" title="Ideas, Planning, & Feedback">🤔</a> <a href="#infra-benfry" title="Infrastructure (Hosting, Build-Tools, etc)">🚇</a> <a href="#mentoring-benfry" title="Mentoring">🧑‍🏫</a> <a href="#maintenance-benfry" title="Maintenance">🚧</a> <a href="#content-benfry" title="Content">🖋</a> <a href="#talk-benfry" title="Talks">📢</a></td>
      <td align="center" valign="top" width="16.66%"><a href="http://reas.com"><img src="https://avatars.githubusercontent.com/u/677774?v=4?s=120" width="120px;" alt="Casey Reas"/><br /><sub><b>Casey Reas</b></sub></a><br /><a href="https://github.com/processing/processing4-carbon-aug-19/commits?author=REAS" title="Code">💻</a> <a href="#ideas-REAS" title="Ideas, Planning, & Feedback">🤔</a> <a href="#infra-REAS" title="Infrastructure (Hosting, Build-Tools, etc)">🚇</a> <a href="#mentoring-REAS" title="Mentoring">🧑‍🏫</a> <a href="#content-REAS" title="Content">🖋</a> <a href="#talk-REAS" title="Talks">📢</a> <a href="#tutorial-REAS" title="Tutorials">✅</a></td>
      <td align="center" valign="top" width="16.66%"><img src="?s=120" width="120px;" alt="anadroid"/><br /><sub><b>anadroid</b></sub><br /><a href="https://github.com/processing/processing4-carbon-aug-19/commits?author=anadroid" title="Code">💻</a></td>
      <td align="center" valign="top" width="16.66%"><img src="?s=120" width="120px;" alt="arnoudvanderleer"/><br /><sub><b>arnoudvanderleer</b></sub><br /><a href="https://github.com/processing/processing4-carbon-aug-19/commits?author=arnoudvanderleer" title="Code">💻</a></td>
      <td align="center" valign="top" width="16.66%"><img src="?s=120" width="120px;" alt="arya-gupta"/><br /><sub><b>arya-gupta</b></sub><br /><a href="https://github.com/processing/processing4-carbon-aug-19/commits?author=arya-gupta" title="Code">💻</a></td>
      <td align="center" valign="top" width="16.66%"><img src="?s=120" width="120px;" alt="bsapozhnikov"/><br /><sub><b>bsapozhnikov</b></sub><br /><a href="https://github.com/processing/processing4-carbon-aug-19/commits?author=bsapozhnikov" title="Code">💻</a></td>
    </tr>
    <tr>
      <td align="center" valign="top" width="16.66%"><img src="?s=120" width="120px;" alt="cathiecode"/><br /><sub><b>cathiecode</b></sub><br /><a href="https://github.com/processing/processing4-carbon-aug-19/commits?author=cathiecode" title="Code">💻</a></td>
      <td align="center" valign="top" width="16.66%"><img src="?s=120" width="120px;" alt="clarvel"/><br /><sub><b>clarvel</b></sub><br /><a href="https://github.com/processing/processing4-carbon-aug-19/commits?author=clarvel" title="Code">💻</a></td>
      <td align="center" valign="top" width="16.66%"><img src="?s=120" width="120px;" alt="codeanticode"/><br /><sub><b>codeanticode</b></sub><br /><a href="https://github.com/processing/processing4-carbon-aug-19/commits?author=codeanticode" title="Code">💻</a></td>
      <td align="center" valign="top" width="16.66%"><img src="?s=120" width="120px;" alt="cyberflamego"/><br /><sub><b>cyberflamego</b></sub><br /><a href="https://github.com/processing/processing4-carbon-aug-19/commits?author=cyberflamego" title="Code">💻</a></td>
      <td align="center" valign="top" width="16.66%"><img src="?s=120" width="120px;" alt="dzaima"/><br /><sub><b>dzaima</b></sub><br /><a href="https://github.com/processing/processing4-carbon-aug-19/commits?author=dzaima" title="Code">💻</a></td>
      <td align="center" valign="top" width="16.66%"><img src="?s=120" width="120px;" alt="efratror"/><br /><sub><b>efratror</b></sub><br /><a href="#maintenance-efratror" title="Maintenance">🚧</a> <a href="https://github.com/processing/processing4-carbon-aug-19/commits?author=efratror" title="Code">💻</a></td>
    </tr>
    <tr>
      <td align="center" valign="top" width="16.66%"><img src="?s=120" width="120px;" alt="ghost"/><br /><sub><b>ghost</b></sub><br /><a href="#maintenance-ghost" title="Maintenance">🚧</a></td>
      <td align="center" valign="top" width="16.66%"><img src="?s=120" width="120px;" alt="guilhermesilveira"/><br /><sub><b>guilhermesilveira</b></sub><br /><a href="https://github.com/processing/processing4-carbon-aug-19/commits?author=guilhermesilveira" title="Code">💻</a></td>
      <td align="center" valign="top" width="16.66%"><img src="?s=120" width="120px;" alt="hectorcarral"/><br /><sub><b>hectorcarral</b></sub><br /><a href="https://github.com/processing/processing4-carbon-aug-19/commits?author=hectorcarral" title="Code">💻</a></td>
      <td align="center" valign="top" width="16.66%"><img src="?s=120" width="120px;" alt="hkiel"/><br /><sub><b>hkiel</b></sub><br /><a href="https://github.com/processing/processing4-carbon-aug-19/commits?author=hkiel" title="Code">💻</a></td>
      <td align="center" valign="top" width="16.66%"><img src="?s=120" width="120px;" alt="hx2a"/><br /><sub><b>hx2a</b></sub><br /><a href="https://github.com/processing/processing4-carbon-aug-19/commits?author=hx2a" title="Code">💻</a></td>
      <td align="center" valign="top" width="16.66%"><img src="?s=120" width="120px;" alt="jaysonh"/><br /><sub><b>jaysonh</b></sub><br /><a href="https://github.com/processing/processing4-carbon-aug-19/commits?author=jaysonh" title="Code">💻</a></td>
    </tr>
    <tr>
      <td align="center" valign="top" width="16.66%"><img src="?s=120" width="120px;" alt="kgtkr"/><br /><sub><b>kgtkr</b></sub><br /><a href="https://github.com/processing/processing4-carbon-aug-19/commits?author=kgtkr" title="Code">💻</a></td>
      <td align="center" valign="top" width="16.66%"><img src="?s=120" width="120px;" alt="knupel"/><br /><sub><b>knupel</b></sub><br /><a href="https://github.com/processing/processing4-carbon-aug-19/commits?author=knupel" title="Code">💻</a></td>
      <td align="center" valign="top" width="16.66%"><img src="?s=120" width="120px;" alt="lesleywagner"/><br /><sub><b>lesleywagner</b></sub><br /><a href="https://github.com/processing/processing4-carbon-aug-19/commits?author=lesleywagner" title="Code">💻</a></td>
      <td align="center" valign="top" width="16.66%"><img src="?s=120" width="120px;" alt="letorbi"/><br /><sub><b>letorbi</b></sub><br /><a href="https://github.com/processing/processing4-carbon-aug-19/commits?author=letorbi" title="Code">💻</a></td>
      <td align="center" valign="top" width="16.66%"><img src="?s=120" width="120px;" alt="maharal"/><br /><sub><b>maharal</b></sub><br /><a href="https://github.com/processing/processing4-carbon-aug-19/commits?author=maharal" title="Code">💻</a></td>
      <td align="center" valign="top" width="16.66%"><img src="?s=120" width="120px;" alt="mglst"/><br /><sub><b>mglst</b></sub><br /><a href="https://github.com/processing/processing4-carbon-aug-19/commits?author=mglst" title="Code">💻</a></td>
    </tr>
    <tr>
      <td align="center" valign="top" width="16.66%"><img src="?s=120" width="120px;" alt="minimaximize"/><br /><sub><b>minimaximize</b></sub><br /><a href="https://github.com/processing/processing4-carbon-aug-19/commits?author=minimaximize" title="Code">💻</a></td>
      <td align="center" valign="top" width="16.66%"><img src="?s=120" width="120px;" alt="montardon"/><br /><sub><b>montardon</b></sub><br /><a href="https://github.com/processing/processing4-carbon-aug-19/commits?author=montardon" title="Code">💻</a></td>
      <td align="center" valign="top" width="16.66%"><img src="?s=120" width="120px;" alt="mvaladas"/><br /><sub><b>mvaladas</b></sub><br /><a href="https://github.com/processing/processing4-carbon-aug-19/commits?author=mvaladas" title="Code">💻</a></td>
      <td align="center" valign="top" width="16.66%"><img src="?s=120" width="120px;" alt="nking07049925"/><br /><sub><b>nking07049925</b></sub><br /><a href="https://github.com/processing/processing4-carbon-aug-19/commits?author=nking07049925" title="Code">💻</a></td>
      <td align="center" valign="top" width="16.66%"><img src="?s=120" width="120px;" alt="oseph"/><br /><sub><b>oseph</b></sub><br /><a href="https://github.com/processing/processing4-carbon-aug-19/commits?author=oseph" title="Code">💻</a></td>
      <td align="center" valign="top" width="16.66%"><img src="?s=120" width="120px;" alt="peonix0"/><br /><sub><b>peonix0</b></sub><br /><a href="https://github.com/processing/processing4-carbon-aug-19/commits?author=peonix0" title="Code">💻</a></td>
    </tr>
    <tr>
      <td align="center" valign="top" width="16.66%"><img src="?s=120" width="120px;" alt="qazcetelic"/><br /><sub><b>qazcetelic</b></sub><br /><a href="https://github.com/processing/processing4-carbon-aug-19/commits?author=qazcetelic" title="Code">💻</a></td>
      <td align="center" valign="top" width="16.66%"><img src="?s=120" width="120px;" alt="robog-two"/><br /><sub><b>robog-two</b></sub><br /><a href="https://github.com/processing/processing4-carbon-aug-19/commits?author=robog-two" title="Code">💻</a></td>
      <td align="center" valign="top" width="16.66%"><img src="?s=120" width="120px;" alt="runemadsen"/><br /><sub><b>runemadsen</b></sub><br /><a href="https://github.com/processing/processing4-carbon-aug-19/commits?author=runemadsen" title="Code">💻</a></td>
      <td align="center" valign="top" width="16.66%"><img src="?s=120" width="120px;" alt="rupeshkumar22"/><br /><sub><b>rupeshkumar22</b></sub><br /><a href="https://github.com/processing/processing4-carbon-aug-19/commits?author=rupeshkumar22" title="Code">💻</a></td>
      <td align="center" valign="top" width="16.66%"><img src="?s=120" width="120px;" alt="rzats"/><br /><sub><b>rzats</b></sub><br /><a href="https://github.com/processing/processing4-carbon-aug-19/commits?author=rzats" title="Code">💻</a></td>
      <td align="center" valign="top" width="16.66%"><img src="?s=120" width="120px;" alt="sableraf"/><br /><sub><b>sableraf</b></sub><br /><a href="https://github.com/processing/processing4-carbon-aug-19/commits?author=sableraf" title="Code">💻</a></td>
    </tr>
    <tr>
      <td align="center" valign="top" width="16.66%"><img src="?s=120" width="120px;" alt="sampottinger"/><br /><sub><b>sampottinger</b></sub><br /><a href="#maintenance-sampottinger" title="Maintenance">🚧</a> <a href="https://github.com/processing/processing4-carbon-aug-19/commits?author=sampottinger" title="Code">💻</a></td>
      <td align="center" valign="top" width="16.66%"><img src="?s=120" width="120px;" alt="sashashura"/><br /><sub><b>sashashura</b></sub><br /><a href="https://github.com/processing/processing4-carbon-aug-19/commits?author=sashashura" title="Code">💻</a></td>
      <td align="center" valign="top" width="16.66%"><img src="?s=120" width="120px;" alt="shahnoor-khan"/><br /><sub><b>shahnoor-khan</b></sub><br /><a href="#maintenance-shahnoor-khan" title="Maintenance">🚧</a></td>
      <td align="center" valign="top" width="16.66%"><img src="?s=120" width="120px;" alt="thomasleplus"/><br /><sub><b>thomasleplus</b></sub><br /><a href="https://github.com/processing/processing4-carbon-aug-19/commits?author=thomasleplus" title="Code">💻</a></td>
      <td align="center" valign="top" width="16.66%"><img src="?s=120" width="120px;" alt="tn8001"/><br /><sub><b>tn8001</b></sub><br /><a href="https://github.com/processing/processing4-carbon-aug-19/commits?author=tn8001" title="Code">💻</a></td>
      <td align="center" valign="top" width="16.66%"><img src="?s=120" width="120px;" alt="trikaphundo"/><br /><sub><b>trikaphundo</b></sub><br /><a href="https://github.com/processing/processing4-carbon-aug-19/commits?author=trikaphundo" title="Code">💻</a></td>
    </tr>
    <tr>
      <td align="center" valign="top" width="16.66%"><img src="?s=120" width="120px;" alt="urbanskimichal"/><br /><sub><b>urbanskimichal</b></sub><br /><a href="https://github.com/processing/processing4-carbon-aug-19/commits?author=urbanskimichal" title="Code">💻</a></td>
      <td align="center" valign="top" width="16.66%"><img src="?s=120" width="120px;" alt="vepo"/><br /><sub><b>vepo</b></sub><br /><a href="https://github.com/processing/processing4-carbon-aug-19/commits?author=vepo" title="Code">💻</a></td>
      <td align="center" valign="top" width="16.66%"><img src="?s=120" width="120px;" alt="villares"/><br /><sub><b>villares</b></sub><br /><a href="https://github.com/processing/processing4-carbon-aug-19/commits?author=villares" title="Code">💻</a></td>
      <td align="center" valign="top" width="16.66%"><img src="?s=120" width="120px;" alt="willrabalais04"/><br /><sub><b>willrabalais04</b></sub><br /><a href="https://github.com/processing/processing4-carbon-aug-19/commits?author=willrabalais04" title="Code">💻</a></td>
      <td align="center" valign="top" width="16.66%"><img src="?s=120" width="120px;" alt="yblake"/><br /><sub><b>yblake</b></sub><br /><a href="https://github.com/processing/processing4-carbon-aug-19/commits?author=yblake" title="Code">💻</a></td>
      <td align="center" valign="top" width="16.66%"><a href="https://github.com/mingness"><img src="https://avatars.githubusercontent.com/u/5671413?v=4?s=120" width="120px;" alt="mingness"/><br /><sub><b>mingness</b></sub></a><br /><a href="#infra-mingness" title="Infrastructure (Hosting, Build-Tools, etc)">🚇</a></td>
    </tr>
  </tbody>
</table>

<!-- markdownlint-restore -->
<!-- prettier-ignore-end -->

<!-- ALL-CONTRIBUTORS-LIST:END -->
