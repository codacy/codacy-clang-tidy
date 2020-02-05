# codacy-clang-tidy

**Warning: This is a work in progress, it is not yet usable.**

A standalone tool that converts [Clang-Tidy](https://clang.llvm.org/extra/clang-tidy/)
diagnostics to Codacy's format. It allows the integration of Clang-Tidy into your Codacy workflow.

## Usage

The upload of results for a commit is done in two steps:
 - uploading all results
 - telling Codacy that it can run the rest of the analysis

For this a [project API](https://support.codacy.com/hc/en-us/articles/207994675-Project-API) token is required.

```bash
export PROJECT_TOKEN="YOUR-TOKEN"
export COMMIT="COMMIT-UUID"

clang-tidy "<clang-tidy-configs>" | \
./codacy-clang-tidy-"<version>" | \
curl -XPOST -L -H "project_token: $PROJECT_TOKEN"
    -H "Content-type: application/json" -d @- \
    "https://api.codacy.com/2.0/commit/$COMMIT/issuesRemoteResults"

curl -XPOST -L -H 'project_token: $PROJECT_TOKEN' \
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
curl -XPOST -L -H "project_token: $PROJECT_TOKEN"
    -H "Content-type: application/json" -d @- \
    "$CODACY_URL/2.0/commit/$COMMIT/issuesRemoteResults"

curl -XPOST -L -H 'project_token: $PROJECT_TOKEN' \
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

- Identify new Static Analysis issues
- Commit and Pull Request Analysis with GitHub, BitBucket/Stash, GitLab (and also direct git repositories)
- Auto-comments on Commits and Pull Requests
- Integrations with Slack, HipChat, Jira, YouTrack
- Track issues Code Style, Security, Error Proneness, Performance, Unused Code and other categories

Codacy also helps keep track of Code Coverage, Code Duplication, and Code Complexity.

Codacy supports PHP, Python, Ruby, Java, JavaScript, and Scala, among others.

### Free for Open Source

Codacy is free for Open Source projects.
