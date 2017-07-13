module.exports = {
    parser: 'babel-eslint',
    parserOptions: {
        sourceType: 'module'
    },
    extends: 'eslint:recommended',
    plugins: ['html'], // required to lint *.vue files
    rules: {
        'no-var': 'warn',
        'no-console': 'off',
        'no-unused-vars': 'warn'
    }
}
