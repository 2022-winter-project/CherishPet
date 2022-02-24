import { StatusBar } from "expo-status-bar";
import { useState } from "react";
import {
  StyleSheet,
  Text,
  View,
  TouchableOpacity,
  Dimensions,
  TextInput,
} from "react-native";
import { Image } from "react-native";
import axios from "axios";

const { width: SCREEN_WIDTH } = Dimensions.get("window");
const { height: SCREEN_HEIGHT } = Dimensions.get("window");

export default function Login({ navigation }) {
  const [id, setId] = useState("");
  const [pw, setPw] = useState("");

  const onPressLogin = async () => {
    //navigation.navigate("Home");
    const data = {
      username: id, // 아이디
      password: pw, // 비밀번호
    };

    try {
      const response = await axios
        .post(`http://192.168.0.12:8080/api/v1/authenticate`, data)
        .then(
          function (response) {
            console.log(response);
            //alert("여긴옴");
            //if (response.data["success"] == true) {

            setName("");
            setId("");
            setPw("");
            alert("로그인되었습니다.");
          }
          //}
        )
        .catch(function (error) {
          alert(error.response.data);
          console.log(error);
          console.log(error.response.data);
        });
    } catch (error) {
      console.log(error);
    }
  };

  return (
    <View style={styles.container}>
      <StatusBar style="auto" />
      <View style={styles.head}>
        <Image style={styles.logo} source={require("../icon/logo_white.png")} />
        <Text style={styles.appText}>CHERISH PET!</Text>
      </View>
      <View style={styles.login}>
        <TextInput
          style={styles.input}
          onChangeText={setId}
          value={id}
          placeholder="아이디"
        />
        <TextInput
          style={styles.input}
          onChangeText={setPw}
          value={pw}
          placeholder="비밀번호"
        />
        <TouchableOpacity
          onPress={() => {
            onPressLogin();
          }}
        >
          <Text style={styles.loginBtn}>로그인</Text>
        </TouchableOpacity>
        <TouchableOpacity onPress={() => navigation.navigate("Signup")}>
          <Text style={styles.signupBtn}>회원가입</Text>
        </TouchableOpacity>
      </View>
      <View style={styles.line} />
      <View style={styles.sns}>
        <Text style={styles.snsText}>SNS로 로그인하기</Text>
        <View style={styles.loginLogo}>
          <Image style={styles.kakao} source={require("../icon/kakao.png")} />
          <Image style={styles.naver} source={require("../icon/naver.png")} />
        </View>
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "white",
    alignItems: "center",
  },
  head: {
    flex: 2,
    justifyContent: "center",
    alignItems: "center",
    backgroundColor: "#D8E1FF",
    width: SCREEN_WIDTH,
  },
  logo: {
    resizeMode: "contain",
    width: SCREEN_WIDTH * 0.3,
    height: SCREEN_HEIGHT * 0.13,
  },
  appText: {
    fontSize: 20,
    fontWeight: "700",
  },
  login: {
    flex: 3,
    width: SCREEN_WIDTH,
    alignItems: "center",
    justifyContent: "center",
  },
  input: {
    borderLeftWidth: 0,
    borderRightWidth: 0,
    borderTopWidth: 0,
    borderBottomWidth: 1,
    marginBottom: SCREEN_HEIGHT * 0.05,
    width: SCREEN_WIDTH * 0.55,
    borderColor: "#9C9C9C",
  },
  loginBtn: {
    backgroundColor: "#D8E1FF",
    fontSize: 17,
    alignItems: "center",
    textAlign: "center",
    paddingBottom: 8,
    paddingTop: 8,
    borderRadius: 50,
    width: SCREEN_WIDTH * 0.55,
    shadowColor: "#000000",
    shadowOpacity: 0.3,
    shadowOffset: { width: 2, height: 2 },
    elevation: 3,
    marginBottom: SCREEN_HEIGHT * 0.05,
  },
  signupBtn: {
    borderBottomWidth: 1,
    alignItems: "center",
    textAlign: "center",
  },
  line: {
    borderBottomColor: "black",
    width: SCREEN_WIDTH * 0.8,
    borderWidth: 0.5,
    opacity: 0.1,
  },
  sns: {
    flex: 2,
    alignItems: "center",
    textAlign: "center",
    justifyContent: "center",
  },
  snsText: {
    fontSize: 18,
    marginBottom: SCREEN_HEIGHT * 0.02,
    opacity: 0.6,
  },
  loginLogo: {
    flexDirection: "row",
    justifyContent: "space-evenly",
    alignItems: "center",
    width: SCREEN_WIDTH,
  },
  kakao: {
    resizeMode: "contain",
    width: SCREEN_WIDTH * 0.15,
    height: SCREEN_HEIGHT * 0.15,
  },
  naver: {
    resizeMode: "contain",
    width: SCREEN_WIDTH * 0.15,
    height: SCREEN_HEIGHT * 0.15,
  },
});
