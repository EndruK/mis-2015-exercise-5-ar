package com.qualcomm.vuforia.samples.SampleApplication.utils;

import java.nio.Buffer;

/**
 * Created by andre on 6/30/15.
 */
public class Plane extends MeshObject{
    private Buffer mVertBuff;
    private Buffer mTexCoordBuff;
    private Buffer mNormBuff;
    private Buffer mIndBuff;

    private int indicesNumber = 0;
    private int verticesNumber = 0;


    public Plane()
    {
        setVerts();
        setTexCoords();
        setNorms();
        setIndices();
    }
    private void setVerts()
    {
        float planeVertices[] = {
                -0.5f, -0.5f, 0.0f, 0.5f, -0.5f, 0.0f, 0.5f, 0.5f, 0.0f, -0.5f, 0.5f, 0.0f
        };
        mVertBuff = fillBuffer(planeVertices);
        verticesNumber = planeVertices.length / 3;
    }
    private void setTexCoords() {
        float planeTexcoords[] = {
                0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f
        };
        mTexCoordBuff = fillBuffer(planeTexcoords);
    }
    private void setNorms() {
        float planeNormals[] = {
                0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f
        };
        mNormBuff = fillBuffer(planeNormals);
    }
    private void setIndices() {
        short planeIndices[] = {
                0, 1, 2, 0, 2, 3
        };
        mIndBuff = fillBuffer(planeIndices);
        indicesNumber = planeIndices.length;
    }
    public int getNumObjectIndex()
    {
        return indicesNumber;
    }
    @Override
    public int getNumObjectVertex()
    {
        return verticesNumber;
    }
    @Override
    public Buffer getBuffer(BUFFER_TYPE bufferType)
    {
        Buffer result = null;
        switch (bufferType)
        {
            case BUFFER_TYPE_VERTEX:
                result = mVertBuff;
                break;
            case BUFFER_TYPE_TEXTURE_COORD:
                result = mTexCoordBuff;
                break;
            case BUFFER_TYPE_NORMALS:
                result = mNormBuff;
                break;
            case BUFFER_TYPE_INDICES:
                result = mIndBuff;
            default:
                break;

        }

        return result;
    }
}
