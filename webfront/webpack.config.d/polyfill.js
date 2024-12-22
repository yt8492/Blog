const nodeExternals = require('webpack-node-externals');
config.externalsPresets = { node: true };
config.externals = nodeExternals();
