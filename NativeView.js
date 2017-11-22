import React from 'react';
import { requireNativeComponent, View } from 'react-native';
import PropTypes from 'prop-types';

const propTypes = {
    someRandomProp: PropTypes.string,
    ...View.propTypes,
};

const NativeView = ({ someRandomProp }) => (
    <RCTNativeView
        style={{ flex: 1 }}
        someRandomProp={'just pass something so we can use setSomeRandomProp as our async action'}
    />
);

NativeView.propTypes = propTypes;
const RCTNativeView = requireNativeComponent('RCTNativeView', NativeView);

export default NativeView;
