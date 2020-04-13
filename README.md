[![Codacy Badge](https://api.codacy.com/project/badge/Grade/925c0ee779c34261b7d1c2935ca95ae5)](https://www.codacy.com/gh/codacy/codacy-clang-tidy?utm_source=github.com&utm_medium=referral&utm_content=codacy/codacy-clang-tidy&utm_campaign=Badge_Grade)
[![Codacy Badge](https://api.codacy.com/project/badge/Coverage/925c0ee779c34261b7d1c2935ca95ae5)](https://www.codacy.com/gh/codacy/codacy-clang-tidy?utm_source=github.com&utm_medium=referral&utm_content=codacy/codacy-clang-tidy&utm_campaign=Badge_Coverage)

# codacy-clang-tidy

**Warning: This is a work in progress, it is not yet usable.**

A standalone tool that converts [Clang-Tidy](https://clang.llvm.org/extra/clang-tidy/)
diagnostics to Codacy's format.

It allows running Clang-Tidy either locally or as part of your CI process and then integrating the results into your Codacy workflow. This way, Codacy will present the results coming from Clang-Tidy alongside all the other code quality information in the dashboards.

Currently, you can only use codacy-clang-tidy on Codacy self-hosted installations.

## Usage

### Requirements

To get your Clang-Tidy results into Codacy you'll need to:
- Enable the setting “Run analysis through build server” under your repository Settings > General > Repository analysis
- Obtain a [Project API token](https://support.codacy.com/hc/en-us/articles/207994675-Project-API)
- Download [codacy-clang-tidy](https://github.com/codacy/codacy-clang-tidy/releases)

### Sending the results to Codacy

Sending the results of running Clang-Tidy to Codacy involves the steps below, which you can automate in your CI build process:

1. Run Clang-Tidy
1. Convert the Clang-Tidy output to a format that the Codacy API accepts
1. Send the results to Codacy
1. Finally, signal that Codacy can use the sent results and start a new analysis


```bash
export PROJECT_TOKEN="YOUR-TOKEN"
export COMMIT="COMMIT-UUID"

clang-tidy "<clang-tidy-configs>" | \
./codacy-clang-tidy-"<version>" | \
curl -XPOST -L -H "project_token: $PROJECT_TOKEN" \
    -H "Content-type: application/json" -d @- \
    "https://api.codacy.com/2.0/commit/$COMMIT/issuesRemoteResults"

curl -XPOST -L -H "project_token: $PROJECT_TOKEN" \
	-H "Content-type: application/json" \
	"https://api.codacy.com/2.0/commit/$COMMIT/resultsFinal"
```

For self-hosted instalations:

```bash
export PROJECT_TOKEN="YOUR-TOKEN"
export COMMIT="COMMIT-UUID"
export CODACY_URL="CODACY-INSTALLATION-URL"

clang-tidy "<clang-tidy-configs>" | \
./codacy-clang-tidy-"<version>" | \
curl -XPOST -L -H "project_token: $PROJECT_TOKEN" \
    -H "Content-type: application/json" -d @- \
    "$CODACY_URL/2.0/commit/$COMMIT/issuesRemoteResults"

curl -XPOST -L -H "project_token: $PROJECT_TOKEN" \
	-H "Content-type: application/json" \
	"$CODACY_URL/2.0/commit/$COMMIT/resultsFinal"
```

## Building

##### Compile

`sbt compile`

##### Format

`sbt ";scalafmt;test:scalafmt;sbt:scalafmt"`

##### Tests

`sbt test`

##### Build native image (requires docker)

`sbt "graalvm-native-image:packageBin"`

##### Build fat-jar

`sbt assembly`

## What is Codacy?

[Codacy](https://www.codacy.com/) is an Automated Code Review Tool that monitors your technical debt, helps you improve your code quality, teaches best practices to your developers, and helps you save time in Code Reviews.

### Among Codacy’s features:

-   Identify new Static Analysis issues
-   Commit and Pull Request Analysis with GitHub, BitBucket/Stash, GitLab (and also direct git repositories)
-   Auto-comments on Commits and Pull Requests
-   Integrations with Slack, HipChat, Jira, YouTrack
-   Track issues Code Style, Security, Error Proneness, Performance, Unused Code and other categories

Codacy also helps keep track of Code Coverage, Code Duplication, and Code Complexity.

Codacy supports PHP, Python, Ruby, Java, JavaScript, and Scala, among others.

### Free for Open Source

Codacy is free for Open Source projects.
