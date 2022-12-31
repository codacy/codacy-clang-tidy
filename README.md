[![Codacy Badge](https://api.codacy.com/project/badge/Grade/925c0ee779c34261b7d1c2935ca95ae5)](https://www.codacy.com/gh/codacy/codacy-clang-tidy?utm_source=github.com&utm_medium=referral&utm_content=codacy/codacy-clang-tidy&utm_campaign=Badge_Grade)
[![Codacy Badge](https://api.codacy.com/project/badge/Coverage/925c0ee779c34261b7d1c2935ca95ae5)](https://www.codacy.com/gh/codacy/codacy-clang-tidy?utm_source=github.com&utm_medium=referral&utm_content=codacy/codacy-clang-tidy&utm_campaign=Badge_Coverage)

# codacy-clang-tidy

A standalone tool that converts [Clang-Tidy](https://clang.llvm.org/extra/clang-tidy/)
diagnostics to Codacy's format.

It allows running Clang-Tidy either locally or as part of your CI process and then integrating the results into your Codacy workflow. This way, Codacy will present the results coming from Clang-Tidy alongside all the other code quality information in the dashboards.

## Usage

### Requirements

To get your Clang-Tidy results into Codacy you'll need to:

-   [Enable Clang-Tidy](https://docs.codacy.com/repositories-configure/configuring-code-patterns/) and configure the corresponding code patterns on your repository **Code patterns** page
-   Enable the setting **Run analysis through build server** on your repository **Settings**, tab **General**, **Repository analysis**
-   Obtain a [project API token](https://docs.codacy.com/codacy-api/api-tokens/#project-api-tokens)
-   Download [codacy-clang-tidy](https://github.com/codacy/codacy-clang-tidy/releases)

### Sending the results to Codacy

Sending the results of running Clang-Tidy to Codacy involves the steps below, which you can automate in your CI build process:

1.  Run Clang-Tidy
2.  Convert the Clang-Tidy output to a format that the Codacy API accepts
3.  Send the results to Codacy
4.  Finally, signal that Codacy can use the sent results and start a new analysis

> When the option **“Run analysis through build server”** is enabled, the Codacy analysis will not start until you call the endpoint `/2.0/commit/{commitUuid}/resultsFinal` signalling that Codacy can use the sent results and start a new analysis.

With script:

```bash
export PROJECT_TOKEN="YOUR-TOKEN"
export COMMIT="COMMIT-UUID"
export CODACY_URL="CODACY-INSTALLATION-URL" # if not defined https://api.codacy.com will be used
export CODACY_CLANG_TIDY_VERSION=0.2.3 # if not defined, latest will be used

clang-tidy "<clang-tidy-configs>" | \
./<codacy-clang-tidy-path>/scripts/send-results.sh # requires a codacy-clang-tidy-"<version>" in the current directory
```

Without script (step-by-step):

```bash
export PROJECT_TOKEN="YOUR-TOKEN"
export COMMIT="COMMIT-UUID"

# 1. Run Clang-Tidy
clang-tidy "<clang-tidy-configs>" | \
# 2. Convert the Clang-Tidy output to a format that the Codacy API accepts
./codacy-clang-tidy-"<version>" | \
# 3. Send the results to Codacy
curl -XPOST -L -H "project-token: $PROJECT_TOKEN" \
    -H "Content-type: application/json" -d @- \
    "https://api.codacy.com/2.0/commit/$COMMIT/issuesRemoteResults"

# 4. Signal that Codacy can use the sent results and start a new analysis
curl -XPOST -L -H "project-token: $PROJECT_TOKEN" \
	-H "Content-type: application/json" \
	"https://api.codacy.com/2.0/commit/$COMMIT/resultsFinal"
```

For self-hosted installations:

```bash
export PROJECT_TOKEN="YOUR-TOKEN"
export COMMIT="COMMIT-UUID"
export CODACY_URL="CODACY-INSTALLATION-URL"

# 1. Run Clang-Tidy
clang-tidy "<clang-tidy-configs>" | \
# 2. Convert the Clang-Tidy output to a format that the Codacy API accepts
./codacy-clang-tidy-"<version>" | \
# 3. Send the results to Codacy
curl -XPOST -L -H "project-token: $PROJECT_TOKEN" \
    -H "Content-type: application/json" -d @- \
    "$CODACY_URL/2.0/commit/$COMMIT/issuesRemoteResults"

# 4. Signal that Codacy can use the sent results and start a new analysis
curl -XPOST -L -H "project-token: $PROJECT_TOKEN" \
	-H "Content-type: application/json" \
	"$CODACY_URL/2.0/commit/$COMMIT/resultsFinal"
```

## Command line flags

`codacy-clang-tidy` accepts as command line arguments:

-   `--encoding <ENCODING>` or `-e <ENCODING>` - encoding to use when parsing the input (default: `UTF-8`). The encoding should be one of the possible [Java Charsets](https://docs.oracle.com/javase/8/docs/api/java/nio/charset/Charset.html#java.nio.charset.Charset).
    Example: 

    ```bash
    ./codacy-clang-tidy-"<version>" --encoding LATIN1
    ```

## Building

##### Compile

`sbt compile`

##### Format

`sbt ";scalafmt;test:scalafmt;sbt:scalafmt"`

##### Tests

`sbt test`

##### Build native image (requires docker)

`sbt "nativeImage"`

##### Build fat-jar

`sbt assembly`

##### Update documentation

If you want to change versions before generating the docs you can do it by
changing the `llvmVersion` value in `doc-generator/src/main/scala/Main.scala`

`sbt doc-generator/run`

## Troubleshooting

### `java.nio.charset.MalformedInputException: Input length = 1` while calling codacy-clang-tidy

Clang-Tidy may return a different encoding as output than the default used by codacy-clang-tidy. You can specify the encoding to use on codacy-clang-tidy:

```bash
./codacy-clang-tidy-"<version>" --encoding <ENCODING>
```

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
test
