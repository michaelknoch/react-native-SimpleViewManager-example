import React, { Component } from 'react';
import { View } from 'react-native';
import NativeView from './NativeView';


export default class App extends Component {
    state = {
        randomString: null,
    }

    render() {
        setTimeout(() => {
            this.setState({randomString: 'pass something so we can use setSomeRandomProp as our async action'})
        }, 200);

        return (
            <View style={{flex: 1}}>
                <NativeView someRandomProp={this.state.randomString} />
            </View>
        );
    }
}
