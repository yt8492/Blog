# AGENTS.md

## 目的

このリポジトリは、複数モジュールで構成された Kotlin ベースのブログシステムです。

- `common`: サーバー、Web フロントエンド、CLI で共有するモデルと JSON 契約
- `server`: Ktor ベースのバックエンドアプリケーション
- `webfront`: Kotlin/JS + React のフロントエンド。webpack でビルドされ、サーバーに同梱される
- `cli`: 記事の投稿、編集、削除を行う macOS 向け Kotlin Multiplatform CLI
- `sandbox`: 実験用モジュール。明示的に対象指定がない限り本番コードとして扱わない

このリポジトリで作業する際は、モジュール間の整合性を崩さないことを優先してください。API 契約やドメインモデルの変更は、通常 `common`、`server`、`webfront`、必要に応じて `cli` まで追従が必要です。

## リポジトリ構成

- `server/src/main/kotlin/com/yt8492/blog/server`
  - `adapter`: controller、repository 実装、JWT や認証まわりの adapter
  - `domain`: repository と service の interface
  - `router`: Ktor のルーティング層
  - `usecase`: use case の interface と実装
  - `di`: Koin モジュール
- `webfront/src/jsMain/kotlin`
  - `api`: フロントエンド用 API クライアント
  - `ui/component`: 再利用する React コンポーネント
  - `ui/page`: ページ単位のコンポーネント
- `common/src/commonMain/kotlin/com/yt8492/blog/common`
  - `model`: 共通ドメインモデル
  - `json`: リクエスト/レスポンス DTO と converter
- `cli/src/macosX64Main/kotlin/com.yt8492.blog.cli`
  - `command`: CLI の各コマンド
  - `api`: CLI 用 API クライアント
  - `service`: 認証やトークン処理

## 作業ルール

- 変更は必要最小限に留め、対象モジュールに閉じた修正を優先する
- `common` にある共有契約を正とし、`server` や `webfront` に同等定義を重複させない
- `server` では既存の層構造 `router -> controller -> use case -> domain/repository` を保つ
- `webfront` ではページ単位の実装は `ui/page`、再利用部品は `ui/component` に置く
- `build/` や `generated/` のような生成物は直接編集しない
- `.idea/` 配下は IDE ローカル状態として扱い、明示的な依頼がない限り変更や巻き戻しをしない
- `kotlin-js-store/yarn.lock` は Kotlin/JS 依存の変更で副作用的に更新されることがある。依存関係を変えていないなら不要な差分として扱う

## ローカル開発

### 必要な環境変数

サーバー起動には次の環境変数が必要です。

- `AUTH_SECRET`
- `BLOG_DOMAIN`
- `GOOGLE_APPLICATION_CREDENTIALS`

`docker-compose.yml` では `./credentials.json` をマウントし、サーバーを `localhost:8080` で起動します。Swagger UI は `localhost:8081` で参照できます。

### よく使うコマンド

リポジトリルートで実行します。

```bash
./gradlew test
./gradlew :server:test
./gradlew :server:run
./gradlew :webfront:jsBrowserDevelopmentExecutableDistribution
./gradlew :webfront:jsBrowserProductionWebpack
./gradlew :cli:assemble
docker compose up --build
```

補足:

- `:server:run` には上記の環境変数が必要
- `server` のビルド時には、`IS_PRODUCTION` の有無に応じて `:webfront:jsBrowserDevelopmentExecutableDistribution` または `:webfront:jsBrowserDistribution` が自動で使われる
- 本番向け Docker イメージ作成は `release-build.sh` を使う

## 変更時の指針

### バックエンド変更

- エンドポイントを追加・変更した場合は、次を必要に応じて一緒に更新する
  - `server` の router / controller / use case
  - `common` の request / response JSON クラス
  - `openapi/openapi.yml`
  - その API を利用している `webfront` と `cli`
- バックエンドの挙動を変える場合は `server/src/test/kotlin` にテスト追加または更新を行う

### フロントエンド変更

- API アクセス処理は `webfront/src/jsMain/kotlin/api` に寄せる
- 似た UI 部品を増やすより、既存コンポーネントの拡張を優先する
- webpack 設定や静的アセットを触ったら、サーバーへの同梱フローが壊れていないか意識する

### 共有モデル変更

- `common/json` や `common/model` のシリアライズ変更は、サーバーと Kotlin/JS クライアントの両方に影響する前提で扱う
- 変更後はモジュール横断のコンパイル影響を確認する

### CLI 変更

- CLI の現行ターゲットは `macosX64`。明示的な依頼がない限り対象プラットフォーム前提を広げない
- 認証まわりの CLI 修正は、サーバー側の認証仕様との整合を確認する

## 検証方針

変更内容に対して、できるだけ狭く適切な検証を選びます。

- ドキュメントのみ: ビルド不要
- バックエンドロジック: `./gradlew :server:test`
- 共有契約変更: `./gradlew test`
- フロントエンド変更: 少なくとも対象モジュールをコンパイルし、可能ならサーバーへの同梱まで確認

検証できなかった場合は、その理由を明示してください。

## このリポジトリ固有の注意点

- `server` のリソース同梱は、`webfront` のビルド成果物を `generated/` にコピーする流れに依存している。この流れは不用意に変えない
