# KOMORANPoSTagger
* **KO**rean **MOR**phological **AN**alyzer **P**art-**o**f-**S**peech **Tagger**
* 오픈소스 형태소 분석기인 [코모란(KOMORAN)](https://github.com/shin285/KOMORAN)을 이용하여 파일 단위 말뭉치의 형태소 분석 결과를 태깅할 수 있습니다.


# 빠른 사용법 (Quick Start)
* KOMORANPoSTagger 프로젝트를 [내려받거나](https://github.com/9bow/KOMORANPoSTagger/archive/master.zip) `git clone` 한 후, 다음과 같이 실행합니다.
```
git clone https://github.com/9bow/KOMORANPoSTagger
cd KOMORANPoSTagger
./gradlew run
```
* 이 때, 분석할 파일명이 주어지지 않으면 기본 입력 파일(`./data/sample.corpus`)을 형태소 분석 및 품사 태깅하여 저장합니다.
* 형태소 분석 및 품사 태깅된 출력 파일은 `./output/` 경로에 `.tagged` 접미사가 붙은 파일명으로 저장됩니다.
    * 예. `./data/sample.corpus` => `./output/sample.corpus.tagged`
* 입력 파일명을 지정하려면 다음과 같이 실행합니다. (단, 입력 파일 내에는 한 줄에 한 문장이 들어갈 수 있도록 저장합니다.)
```
./gradlew run -P corpus='filepath/filename'
```


# 사용법 (Usage)
* KOMORANPoSTagger 프로젝트를 [내려받거나](https://github.com/9bow/KOMORANPoSTagger/archive/master.zip) `git clone`한 후, 다음과 같이 실행 가능한 jar 파일을 생성합니다.
```
git clone https://github.com/9bow/KOMORANPoSTagger
cd KOMORANPoSTagger
./gradlew jar
```
* 생성된 jar 파일(`build/libs/KOMORANPoSTagger.jar`)을 실행합니다.
```
java -jar ./build/libs/KOMORANPoSTagger.jar
```
* 이 때, 분석할 파일명이 주어지지 않으면 기본 입력 파일(`./data/sample.corpus`)을 형태소 분석 및 품사 태깅하여 저장합니다.
* 형태소 분석 및 품사 태깅된 출력 파일은 `./output/` 경로에 `.tagged` 접미사가 붙은 파일명으로 저장됩니다.
    * 예. `./data/sample.corpus` => `./output/sample.corpus.tagged`
* 입력 파일을 지정하려면 다음과 같이 실행합니다. (단, 입력 파일 내에는 한 줄에 한 문장이 들어갈 수 있도록 저장합니다.)
```
java -jar ./build/libs/KOMORANPoSTagger.jar filepath/filename
```


# 설정 변경하기 (Using config.properties)
* Config 파일(`src/main/resources/config.properties`)에서 아래의 기본 설정을 변경할 수 있습니다.
* 지원하는 설정의 종류는 다음과 같습니다.

| 설정명 | 설명 | 기본값 |
|------|-----|------|
| `input.sample` | (입력 파일이 지정되지 않았을 때) 기본 입력 파일 | `./data/sample.corpus` |
| `input.charset` | 입력 파일의 인코딩 | `UTF-8` |
| `output.dirpath` | 출력 파일의 저장 경로 | `./output/` |
| `output.postfix` | 출력 파일의 접미사 (파일명은 입력 파일과 동일) | `.tagged` |
| `output.charset` | 입력 파일의 인코딩 | `UTF-8` |
| `delimiter.pos` | 품사 앞의 구분자 | `/` |
| `delimiter.item` | 단어 간의 구분자 | 공백 문자(Space, `\u0020`) |
| `delimiter.line` | 입력 줄 간의 구분자 | 개행 문자(Newline, `\n`) |
| `model.type` | 형태소 분석에 사용할 모델의 종류 (full, light) | `full` |
| `remove.space` | 고유명사 등, 형태소에 공백이 포함된 경우 제거 여부 (true, false) | `true` |


# 입출력 파일 예시 (Example of input/output file)
* 입력 파일의 예
```
제1조
① 대한민국은 민주공화국이다.
② 대한민국의 주권은 국민에게 있고, 모든 권력은 국민으로부터 나온다.
```

* 출력 파일의 예
```
제/XPN 1/SN 조/NR
①/SW 대한민국/NNP 은/JX 민주공화국/NNP 이/VCP 다/EF ./SF
②/SW 대한민국/NNP 의/JKG 주권/NNP 은/JX 국민/NNG 에게/JKB 있/VV 고/EC ,/SP 모든/MM 권력/NNG 은/JX 국민/NNG 으로부터/JKB 나오/VV ㄴ다/EF ./SF
```